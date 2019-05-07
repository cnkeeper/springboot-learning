package com.github.cnkeep.springboot.redis.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 描述: 互斥锁
 * <pre>
 *     ## 特点
 *     1. 不支持重入
 *     2. 采用发布订阅功能实时通知其他节点获取锁
 *     3. 获取锁失败时，阻塞时间动态设置
 *     4. 直接实现jdk原有Lock, 使用无障碍
 *
 *     ## 不足
 *     1. 不支持重入
 *     2. 假如本地任务耗时长，执行期间锁无法自动续约
 *     3. 未剔除依赖，强依赖于spring-data-redis(暂时可以忽略，后期提供redis协议客户端)
 *
 *
 *
 *     ##使用
 *     Lock lock = new MutexDistributeLock("lock-id");
 *     lock.lock();
 *     try{
 *         //do something
 *     }finally{
 *         lock.unlock();
 *     }
 * </pre>
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/21
 */
public class MutexDistributeLock implements Lock {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MutexDistributeLock.class);

    private final StringRedisTemplate template = RedisTemplateHolder.stringRedisTemplate();
    private static DefaultRedisScript<Long> unlockScript;
    private static DefaultRedisScript<Long> lockScript;
    /**
     * 节点标识
     */
    private String nodeTag;
    /**
     * 锁标识
     */
    private String key;
    /**
     * 释放锁消息通道
     */
    private byte[] channel;
    private final Semaphore monitor = new Semaphore(1);
    /**
     * 默认加锁时长为30s
     */
    private long lockTime = 30;


    static {
        // 只初始化一次
        unlockScript = new DefaultRedisScript<>();
        unlockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("mutex/unlock.lua")));
        unlockScript.setResultType(Long.class);

        lockScript = new DefaultRedisScript<>();
        lockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("mutex/lock.lua")));
        lockScript.setResultType(Long.class);
    }

    /**
     * 锁标识
     *
     * @param key
     */
    public MutexDistributeLock(String key) {
        this.key = buildKey(key);
        init();
    }

    /**
     * @param key      锁标识
     * @param holeLockTime 加锁时长，单位：s
     */
    public MutexDistributeLock(String key, long holeLockTime) {
        this.key = buildKey(key);
        this.lockTime = holeLockTime;
        init();
    }

    private String buildKey(String key) {
        return "lock:" + key;
    }

    private void init() {
        nodeTag = UUID.randomUUID().toString();
        channel = (RedisTemplateHolder.CHANNEL_PREFIX + key).getBytes();
    }

    @Override
    public void lock() {
        try {
            lockInterruptibly();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        if (!tryLock()) {
            watchLock();
            for (; ; ) {
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                long pttl = doTryLock();
                // lock success
                if (0 > pttl) {
                    return;
                }

                //block wait
                monitor.tryAcquire(pttl, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override
    public boolean tryLock() {
        return 0 > doTryLock();
    }

    /**
     * 尝试加锁
     *
     * @return -1：加锁成功返回-1，否则返回锁剩余有效期：ms
     */
    private long doTryLock() {
        Long result = template.execute(lockScript, Collections.singletonList(key), nodeTag, Long.toString(TimeUnit.SECONDS.toMillis(lockTime)));
        LOGGER.info(">>>>>>>>>>doTryLock result:{}", result);
        return Optional.ofNullable(result).orElse(0L).longValue();
    }

    /**
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        long remainWaitTime = unit.toMillis(time);
        if (!tryLock()) {
            // check trylock timeout
            remainWaitTime -= (System.currentTimeMillis() - startTime);
            if (remainWaitTime <= 0) {
                return false;
            }

            watchLock();
            for (; ; ) {
                startTime = System.currentTimeMillis();
                long pttl = doTryLock();
                if (0 > pttl) {
                    return true;
                }

                // check trylock timeout
                remainWaitTime -= (System.currentTimeMillis() - startTime);
                if (remainWaitTime <= 0) {
                    return false;
                }

                startTime = System.currentTimeMillis();
                if (pttl > 0 && remainWaitTime < pttl) {
                    monitor.tryAcquire(remainWaitTime, TimeUnit.MILLISECONDS);
                } else {
                    monitor.tryAcquire(pttl, TimeUnit.MILLISECONDS);
                }

                // check block timeout
                remainWaitTime -= (System.currentTimeMillis() - startTime);
                if (remainWaitTime <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void unlock() {
        Long result = template.execute(unlockScript, Collections.singletonList(key), nodeTag);
        if (0 != result) {
            throw new RuntimeException(MessageFormat.format("Error to release lock[{0}], this node[{1}] is not Lock's owner!", key, nodeTag));
        }
        // notify all of node can try to get lock
        RedisTemplateHolder.connection().publish(channel, "release".getBytes());
        monitor.release();
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException("Distributed Lock unsupported new Condition!");
    }

    /**
     * 通过发布订阅模式监听锁释放事件
     */
    private void watchLock() {
        RedisTemplateHolder.connection().pSubscribe((message, pattern) -> {
            LOGGER.info("receive message:{}", message);
            // notify can to tryLock
            monitor.release();
        }, channel);
    }
}
