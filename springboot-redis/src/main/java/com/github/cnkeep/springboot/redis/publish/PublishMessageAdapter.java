package com.github.cnkeep.springboot.redis.publish;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.Topic;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/22
 */
public abstract class PublishMessageAdapter {

    public void publish(Object message) {
        redisTemplate().convertAndSend(topic().getTopic(), message);
    }

    /**
     *
     */
    public abstract RedisTemplate redisTemplate();

    /**
     * 返回主题，请勿使用直接 return new 的方式
     * @return
     */
    public abstract Topic topic();
}
