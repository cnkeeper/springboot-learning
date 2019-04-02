package com.github.cnkeep.springboot.redis.lock;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 测试redis分布式锁
 */
public class RedisLockTest {
    @Test
    public void test(){
        RedisTemplateHolder.connection().pSubscribe((message, pattern) -> {
            System.out.println(new String(message.getBody()));
        },"*".getBytes());
        Semaphore semaphore = new Semaphore(0);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String key = "lock";
        Runnable task1 = () -> {
            RedisLock lock = new RedisLock(key);
            lock.lock();
            try {
                System.out.println("do something!");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.release();
        };

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(task1);
        threadPool.execute(task1);
        threadPool.shutdown();
    }
}