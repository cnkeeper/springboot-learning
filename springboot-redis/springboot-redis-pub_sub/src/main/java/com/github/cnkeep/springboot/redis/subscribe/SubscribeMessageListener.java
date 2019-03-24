package com.github.cnkeep.springboot.redis.subscribe;

import org.springframework.data.redis.listener.Topic;

/**
 * 描述: redis订阅消息监听器
 * <pre>
 *     {@code
 *     @Component
 *      public class SubscribeMessageListenerTest implements SubscribeMessageListener {
 *          private static final Logger LOGGER = LoggerFactory.getLogger(SubscribeMessageListenerTest.class);
 *          private Topic topic = new PatternTopic("Topic");
 *          @Override
 *          public Topic topic() {
 *              return topic;
 *          }
 *          @Override
 *          public void receiveMessage(Object message, String channel) {
 *              LOGGER.info("get message[{}], from channel[{}]",message,channel);
 *          }
 *      }
 *     }
 * </pre>
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
     * 接收订阅消息回调函数
     * @param message
     * @param channel the channel of where message comes from
     */
    void receiveMessage(T message, String channel);
}
