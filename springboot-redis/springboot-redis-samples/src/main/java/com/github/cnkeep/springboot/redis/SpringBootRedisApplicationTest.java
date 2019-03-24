package com.github.cnkeep.springboot.redis;

import com.github.cnkeep.springboot.redis.config.EnableRedisPubAndSub;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Semaphore;

/**
 * 描述~
 *
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date ${DATE}
 */
@SpringBootApplication
@EnableRedisPubAndSub
public class SpringBootRedisApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisApplicationTest.class, args);
        Semaphore semaphore = new Semaphore(0);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

