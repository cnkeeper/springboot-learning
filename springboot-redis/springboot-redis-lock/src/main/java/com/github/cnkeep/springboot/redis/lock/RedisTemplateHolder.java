package com.github.cnkeep.springboot.redis.lock;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 描述~
 *
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2019/2/27
 */
public final class RedisTemplateHolder {
    private static RedisTemplateFactory redisTemplateFactory;
    /**
     * 监听通道
     */
    public static final String CHANNEL_PREFIX = LettuceRedisTemplateFactory.CHANNEL_PREFIX;

    private RedisTemplateHolder() {}

    static {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("172.16.22.135", 6379);
        redisTemplateFactory = new LettuceRedisTemplateFactory(configuration);
    }

    public final static RedisTemplate<Object, Object> redisTemplate() {
        return redisTemplateFactory.redisTemplate();
    }

    public final static StringRedisTemplate stringRedisTemplate() {
        return redisTemplateFactory.stringRedisTemplate();
    }

    public final static RedisConnection connection() {
        return redisTemplateFactory.connection();
    }
}
