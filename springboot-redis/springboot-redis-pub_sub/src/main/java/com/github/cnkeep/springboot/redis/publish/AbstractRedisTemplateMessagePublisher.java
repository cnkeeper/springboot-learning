package com.github.cnkeep.springboot.redis.publish;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * 描述: 基于RedisTemplate的消息发布抽象类
 *
 * @author <a href="mailto:zhangleili@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2019/3/24
 */
public abstract class AbstractRedisTemplateMessagePublisher<T> implements MessagePublisher<T> {
    @Override
    public void publish(T message) {
        redisTemplate().convertAndSend(topic().getTopic(), message);
    }

    /**
     * inject RedisTemplate
     */
    protected abstract RedisTemplate redisTemplate();
}
