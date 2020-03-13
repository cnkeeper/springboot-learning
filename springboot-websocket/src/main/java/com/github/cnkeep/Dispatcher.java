package com.github.cnkeep;

import javax.websocket.Session;

/**
 * @description: 消息处理器
 * @author: <a href="mailto:zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @date: 2020-03-13
 * @version: v1.1.8
 **/
public interface Dispatcher<R> {
    /**
     * 消息处理器
     *
     * @param session
     * @param message
     * @return
     */
    R dispatcher(Session session, String message);
}
