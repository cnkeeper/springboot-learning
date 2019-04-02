package com.github.cnkeep.springboot.redis.lock;

import io.lettuce.core.AbstractRedisClient;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Field;

/**
 * 描述~
 *
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2019/2/27
 */
public class RedisTemplateHolder {
    private static StringRedisTemplate stringRedisTemplate;
    private static RedisTemplate redisTemplate;
    private static LettuceConnectionFactory redisConnectionFactory;
    /** 监听通道*/
    public static final String CHANNEL_PREFIX = "lock-channel:";

    static {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("172.16.22.135",6379);
        redisConnectionFactory = new LettuceConnectionFactory(configuration);
        redisConnectionFactory.afterPropertiesSet();

        redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();

        stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        stringRedisTemplate.afterPropertiesSet();
    }

    public static RedisTemplate<Object, Object> redisTemplate() {
        return redisTemplate;
    }

    public static StringRedisTemplate stringRedisTemplate() {
        return stringRedisTemplate;
    }

    public static RedisConnection connection(){
        return redisConnectionFactory.getConnection();
    }
}
