package com.github.cnkeep.springboot.redis.subscribe;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.lang.Nullable;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/22
 */
public class DelegatingMessageListenerAdapter implements MessageListener {
    private MessageListenerAdapter messageListenerAdapter;
    private Topic topic;

    public DelegatingMessageListenerAdapter(MessageListenerAdapter messageListenerAdapter,Topic topic) {
        this.messageListenerAdapter = messageListenerAdapter;
        this.topic = topic;
        this.messageListenerAdapter.setSerializer(new GenericJackson2JsonRedisSerializer());
        messageListenerAdapter.afterPropertiesSet();
    }

    public DelegatingMessageListenerAdapter(SubscribeMessageListener messageListener) {
        this(new MessageListenerAdapter(messageListener,"receiveMessage"),messageListener.topic());
    }

    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        this.messageListenerAdapter.onMessage(message,pattern);
    }

    public Topic getTopic() {
        return topic;
    }
}
