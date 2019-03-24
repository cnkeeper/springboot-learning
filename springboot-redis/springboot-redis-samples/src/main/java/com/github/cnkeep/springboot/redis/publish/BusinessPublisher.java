package com.github.cnkeep.springboot.redis.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Component;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/22
 */
@Component
public class BusinessPublisher extends AbstractRedisTemplateMessagePublisher {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RedisTemplate redisTemplate() {
        return redisTemplate;
    }

    @Override
    public Topic topic() {
        return new ChannelTopic("Topic");
    }
}
