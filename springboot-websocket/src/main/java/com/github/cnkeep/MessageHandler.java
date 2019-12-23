package com.github.cnkeep;

import javax.websocket.Session;

/**
 * @description: websocket  业务处理接口
 * @author: <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @date: 2019-09-04
 * @version: v1.1.8
 **/
public interface MessageHandler<T> {
    /**
     * 处理业务消息
     * @param session
     * @param message
     * @return
     */
    GeneralWebSocketResponse handle(Session session, T message);

    /**
     * 处理的消息类型
     * @return
     */
    MessageType acceptType();
}
