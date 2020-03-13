package com.github.cnkeep.dispatcher;

import com.alibaba.fastjson.JSON;
import com.github.cnkeep.Dispatcher;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import java.util.Objects;

/**
 * @description:  通用消息处理器，自定义的处理器继承该类实现方法即可
 * @author: <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @date: 2019-09-05
 * @version: v1.1.8
 **/
@Slf4j
public abstract class AbstractMessageDispatcher<R> implements Dispatcher<R> {

    /**
     * Ws连接建立。
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        log.info("onOpen sessionId:{}", session.getId());
    }

    /**
     * 收到Ws消息。
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("onMessage sessionId:{}, message:{}", session.getId(), message);
        R response = dispatcher(session, message);

        if(Objects.isNull(response)){
            return;
        }

        try {
            session.getBasicRemote().sendText(JSON.toJSONString(response));
        } catch (Exception e) {
            log.error("error to send Message[{}]",response);
        }
    }

    /**
     * Ws连接关闭。
     */
    @OnClose
    public void onClose(Session session) {
        log.info("onClose sessionId:{}", session.getId());
    }

    /**
     * Ws发生错误时调用的方法。
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.info("onError sessionId:{}, errorMsg:{}", session.getId(), error.getMessage());
    }

}
