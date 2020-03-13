package com.github.cnkeep.support.spring;

import com.github.cnkeep.MessageHandler;
import com.github.cnkeep.MessageInterceptor;
import com.github.cnkeep.MessageType;
import com.github.cnkeep.WsApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description: SpringApplicationContext
 * @author: <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @date: 2019-09-04
 * @version: v1..1.8
 **/
@Component
@Slf4j
public class SpringWsApplicationContext implements ApplicationContextAware, WsApplicationContext {
    private static List<MessageInterceptor> wsMessageInterceptors = new CopyOnWriteArrayList<>();
    private static Map<MessageType, MessageHandler> wsHandlerMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        registerWsMessageHandler(applicationContext);
        registerWsMessageInterceptor(applicationContext);
    }

    private void registerWsMessageHandler(ApplicationContext applicationContext) {
        Map<String, MessageHandler> beans = applicationContext.getBeansOfType(MessageHandler.class);
        for (MessageHandler messageHandler : beans.values()) {
            log.info("register messageHandler: class=[{}], type=[{}]", messageHandler.getClass(), messageHandler.acceptType());
            wsHandlerMap.put(messageHandler.acceptType(), messageHandler);
        }
        wsHandlerMap = Collections.unmodifiableMap(wsHandlerMap);
    }

    private void registerWsMessageInterceptor(ApplicationContext applicationContext) {
        Map<String, MessageInterceptor> beans = applicationContext.getBeansOfType(MessageInterceptor.class);
        for (MessageInterceptor interceptor : beans.values()) {
            log.info("register messageInterceptor: class=[{}]", interceptor.getClass());
            wsMessageInterceptors.add(interceptor);
        }

        wsMessageInterceptors = Collections.unmodifiableList(wsMessageInterceptors);
    }

    public static Map<MessageType, MessageHandler> getWsHandlerMap() {
        return wsHandlerMap;
    }

    public static List<MessageInterceptor> getWsMessageInterceptors() {
        return wsMessageInterceptors;
    }

    @Override
    public void registerMessageHandler(MessageHandler messageHandler) {
        wsHandlerMap.put(messageHandler.acceptType(), messageHandler);
    }

    @Override
    public void registerMessageInterceptor(MessageInterceptor messageInterceptor) {
        wsMessageInterceptors.add(messageInterceptor);
    }

    @Override
    public MessageHandler getMessageHandler(MessageType messageType) {
        return wsHandlerMap.get(messageType);
    }

    @Override
    public Map<MessageType, MessageHandler> getMessageHandlerMap() {
        return wsHandlerMap;
    }

    @Override
    public List<MessageInterceptor> getMessageInterceptorList() {
        return wsMessageInterceptors;
    }
}
