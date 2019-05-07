package com.github.cnkeep.springboot.redis.lock;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 描述: Lettcue 实现的RedisTemplateFactory, 请使用单例模式
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/3
 */
public class LettuceRedisTemplateFactory implements RedisTemplateFactory {
    private StringRedisTemplate stringRedisTemplate;
    private RedisTemplate redisTemplate;
    private LettuceConnectionFactory redisConnectionFactory;
    /**
     * 监听通道
     */
    public static final String CHANNEL_PREFIX = "lock-channel:";

    public LettuceRedisTemplateFactory(RedisStandaloneConfiguration configuration) {
        redisConnectionFactory = new LettuceConnectionFactory(configuration);
        redisConnectionFactory.afterPropertiesSet();

        redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();

        stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        stringRedisTemplate.afterPropertiesSet();
    }

    @Override
    public RedisTemplate<Object, Object> redisTemplate() {
        return redisTemplate;
    }

    @Override
    public StringRedisTemplate stringRedisTemplate() {
        return stringRedisTemplate;
    }

    @Override
    public RedisConnection connection() {
        return redisConnectionFactory.getConnection();
    }
}
