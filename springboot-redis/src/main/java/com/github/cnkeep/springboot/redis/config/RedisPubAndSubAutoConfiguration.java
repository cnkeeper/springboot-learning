package com.github.cnkeep.springboot.redis.config;

import com.github.cnkeep.springboot.redis.subscribe.DelegatingMessageListenerAdapter;
import com.github.cnkeep.springboot.redis.subscribe.SubscribeMessageListener;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: Redis 消息订阅自动配置
 * <pre>
 *     1. 自动加载注入实现了SubscribeMessageListener的类
 * </pre>
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/22
 */
@Configuration
@ConditionalOnClass({RedisConnectionFactory.class})
public class RedisPubAndSubAutoConfiguration implements ApplicationContextAware{

    @Bean
    @ConditionalOnMissingBean({RedisConnectionFactory.class})
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        listenerAdapters().stream().forEach(e->container.addMessageListener(e,e.getTopic()));
        return container;
    }

    @Bean
    @ConditionalOnMissingBean({RedisConnectionFactory.class})
    public List<DelegatingMessageListenerAdapter> listenerAdapters(){
        return applicationContext.getBeansOfType(SubscribeMessageListener.class)
                .entrySet()
                .stream()
                .map(e->new DelegatingMessageListenerAdapter(e.getValue()))
                .collect(Collectors.toList());
    }


    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
