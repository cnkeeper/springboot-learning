package com.github.cnkeep.springboot.redis.lock;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/3
 */
public interface RedisTemplateFactory {
    RedisTemplate<Object, Object> redisTemplate();

    StringRedisTemplate stringRedisTemplate();

    RedisConnection connection();
}
