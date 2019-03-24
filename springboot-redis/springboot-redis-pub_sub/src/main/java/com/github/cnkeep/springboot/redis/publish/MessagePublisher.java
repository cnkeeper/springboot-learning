package com.github.cnkeep.springboot.redis.publish;

import org.springframework.data.redis.listener.Topic;

/**
 * 描述： 消息发布接口
 *
 * @author <a href="mailto:zhangleili@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2019/3/24
 */
public interface MessagePublisher<T> {
    /**
     * 发布消息
     *
     * @param message
     */
    void publish(T message);

    /**
     * 消息发布的频道
     *
     * @return
     */
    Topic topic();
}
