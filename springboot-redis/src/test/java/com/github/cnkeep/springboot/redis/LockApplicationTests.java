package com.github.cnkeep.springboot.redis;

import com.github.cnkeep.springboot.redis.lock.RedisLock;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRedisApplication.class)
public class LockApplicationTests {

    @Test
    public void contextLoads() throws InterruptedException {
        final int nodeCount = 5;
        //Junit不支持多线程，采用countDownLatch阻塞,防止提前终止退出
        CountDownLatch countDownLatch = new CountDownLatch(nodeCount);
        RedisLock lock = new RedisLock();
        lock.setKey("Lock_001");

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int index = 0; index < nodeCount; index++) {
            threadPool.execute(() -> new Node(lock, countDownLatch).doWork());
        }
        countDownLatch.await();
        threadPool.shutdown();
    }

    @AllArgsConstructor
    public static class Node {
        private RedisLock lock;
        private CountDownLatch countDownLatch;

        public void doWork() {
            try {
                lock.lock();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("finish work");
                lock.release();
            } finally {
                countDownLatch.countDown();
            }
        }
    }
}

