package com.github.cnkeep.springboot.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 描述~
 *
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2019/2/27
 */
@Component
public class RedisTemplateHolder {
    static StringRedisTemplate REDIS_TEMPLATE;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate){
        RedisTemplateHolder.REDIS_TEMPLATE = redisTemplate;
    }
}
