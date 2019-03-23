package com.github.cnkeep.springboot.redis.subscribe;

import com.github.cnkeep.springboot.redis.lock.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.listener.PatternTopic;
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
public class SubscribeMessageListenerTest implements SubscribeMessageListener<RedisLock> {
    /**
     * Logger
     * */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscribeMessageListenerTest.class);

    @Override
    public Topic topic() {
        return new PatternTopic("Topic");
    }

    @Override
    public void receiveMessage(RedisLock message, String channel) {
        LOGGER.info("get message[{}], from channel[{}]",message,channel);
    }
}
