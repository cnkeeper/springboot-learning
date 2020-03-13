package com.github.cnkeep;


import java.util.List;
import java.util.Map;

/**
 * @description: websocket容器
 * @author: <a href="mailto:zhangleili@lizhi.fm">LeiLi.Zhang</a>
 * @date: 2020-03-13
 * @version: v1.1.8
 **/
public interface WsApplicationContext {

    void registerMessageHandler(MessageHandler messageHandler);

    void registerMessageInterceptor(MessageInterceptor messageInterceptor);

    MessageHandler getMessageHandler(MessageType messageType);

    Map<MessageType, MessageHandler> getMessageHandlerMap();

    List<MessageInterceptor> getMessageInterceptorList();
}
