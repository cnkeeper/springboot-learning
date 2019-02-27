package com.github.cnkeep.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述~
 *
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date ${DATE}
 */
@SpringBootApplication
public class SpringBootRedisApplication implements CommandLineRunner {

    /**
     * 自动注入
     */
    private static StringRedisTemplate template;

    @Autowired
    public void setTemplate(StringRedisTemplate template){
        SpringBootRedisApplication.template = template;
    }

    @Override
    public void run(String... args) throws Exception {
        ValueOperations<String, String> ops = template.opsForValue();
        String key = "spring.boot.redis.test";
        if (!template.hasKey(key)) {
            ops.set(key, "foo");
        }
        System.out.println("Found key " + key + ", value=" + ops.get(key));


    }



    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisApplication.class, args);
    }

}

