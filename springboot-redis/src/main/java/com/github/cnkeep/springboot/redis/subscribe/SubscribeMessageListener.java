package com.github.cnkeep.springboot.redis.subscribe;

import org.springframework.data.redis.listener.Topic;

/**
 * 描述: redis订阅消息监听器
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/22
 */
public interface SubscribeMessageListener<T>{
    /**
     * 返回订阅的主题
     * @return
     */
    Topic topic();

    /**
     * 接收订阅消息
     * @param message
     * @param channel
     */
    void receiveMessage(T message, String channel);
}
