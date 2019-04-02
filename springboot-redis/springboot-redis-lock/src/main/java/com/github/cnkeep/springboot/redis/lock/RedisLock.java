package com.github.cnkeep.springboot.redis.lock;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 描述: 基于Redis的分布式锁，不可重入
 * 采用的是简单的定时获取锁策略，不具有灵活性，不推荐，推荐
 * @see MutexLock
 *
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2019/2/27
 */
@Deprecated
public class RedisLock {
    private final StringRedisTemplate template = RedisTemplateHolder.stringRedisTemplate();
    private String nodeTag;

    private String key;

    /**
     * 加锁时长，超过此时间后锁将会自动释放，默认10分钟
     */
    private long lockTime = 10 * 60 * 1000;

    public RedisLock() {
        this.nodeTag = UUID.randomUUID().toString().replaceAll("-", "");
        this.key = "KEY_" + nodeTag;
    }

    public RedisLock(String key) {
        this();
        this.key = key;
    }

    public RedisLock(String nodeTag, String key) {
        this(nodeTag);
        this.key = key;
    }

    public boolean lock() {
        for (; ; ) {
            if (doLock(TimeUnit.SECONDS, lockTime)) {
                return true;
            }

            sleep(TimeUnit.SECONDS, 5L);
        }
    }

    /**
     * 申请加锁，超时时间内未获取成功则返回false,否则返回true
     *
     * @param timeUnit
     * @param timeout
     * @return
     */
    public boolean tryLock(TimeUnit timeUnit, long timeout) {
        long deadLine = System.currentTimeMillis() + timeUnit.toMillis(timeout);
        for (; ; ) {
            if (doLock(TimeUnit.SECONDS, lockTime)) {
                return true;
            } else if (System.currentTimeMillis() > deadLine) {
                return false;
            }

            sleep(TimeUnit.SECONDS, 5L);
        }
    }


    /**
     * 申请加锁，立即返回结果
     *
     * @return
     */
    public boolean tryLock() {
        return doLock(TimeUnit.SECONDS, lockTime);
    }

    /**
     * @param timeUnit
     * @param timeout
     * @return
     */
    private boolean doLock(TimeUnit timeUnit, long timeout) {
        Boolean locked = template.opsForValue().setIfAbsent(key, nodeTag, timeout, timeUnit);
        System.out.println(MessageFormat.format("node:{0}, try to lock with result:{1},time:{2}", nodeTag, locked, LocalDateTime.now().toString()));
        return locked;
    }

    public boolean release() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("mutex/unlock.lua")));
        redisScript.setResultType(Long.class);

        Long result = template.execute(redisScript, Collections.singletonList(key), nodeTag);
        return result == 0;
    }


    private void sleep(TimeUnit timeUnit, long time) {
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
