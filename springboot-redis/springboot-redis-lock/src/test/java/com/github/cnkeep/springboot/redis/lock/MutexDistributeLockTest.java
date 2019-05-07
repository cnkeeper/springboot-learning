package com.github.cnkeep.springboot.redis.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class MutexDistributeLockTest {
    public static void main(String[] args) {
        String key = "Job";
        Runnable task1 = () -> {
            Lock lock = new MutexDistributeLock(key);

            try {
                boolean locked = lock.tryLock(2, TimeUnit.SECONDS);
                if (!locked) {
                    System.err.println("lock failed!");
                    return;
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                System.out.println("do something!");
                TimeUnit.SECONDS.sleep(10);
                System.out.println("do over!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(task1);
        threadPool.execute(task1);
        threadPool.shutdown();
    }
}