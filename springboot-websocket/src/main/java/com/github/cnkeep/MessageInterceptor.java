package com.github.cnkeep;

import javax.websocket.Session;

/**
 * @description: websocket 拦截器, 请注意注意内部变量的线程安全
 * @author: <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @date: 2019-09-04
 * @version: v1.1.8
 **/
public interface MessageInterceptor {
    /**
     * 前置处理
     *
     * @param session
     * @param request
     * @param response
     * @return false中断操作理解返回
     */
    boolean pre(Session session, GeneralWebSocketRequest request, GeneralWebSocketResponse response);

    /**
     * 后置处理
     *
     * @param session
     * @param request
     * @param response
     * @return false 中断操作理解返回
     */
    boolean post(Session session, GeneralWebSocketRequest request, GeneralWebSocketResponse response);
}
