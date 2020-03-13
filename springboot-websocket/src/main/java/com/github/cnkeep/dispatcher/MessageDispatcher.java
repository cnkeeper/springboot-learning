package com.github.cnkeep.dispatcher;

import com.alibaba.fastjson.JSON;
import com.github.cnkeep.*;
import com.github.cnkeep.constant.DomainConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Objects;

/**
 * @description: websocket消息处理器
 * @author: <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @date: 2019-09-04
 * @version: v1.1.8
 **/
@Slf4j
@Component
@ServerEndpoint(value = DomainConstants.DEFAULT_DOMAIN)
public class MessageDispatcher extends AbstractMessageDispatcher<GeneralWebSocketResponse> {
    private static MessageInterceptorChain interceptorChain;

    @Autowired
    private static WsApplicationContext wsApplicationContext;

    @PostConstruct
    public void init() {
        log.info("MessageDispatcher init.");
        interceptorChain = new MessageInterceptorChain();
        List<MessageInterceptor> interceptors = wsApplicationContext.getMessageInterceptorList();
        interceptorChain.setInterceptors(interceptors);
    }

    /**
     * @param session
     * @param json
     * @return
     */
    @Override
    public GeneralWebSocketResponse dispatcher(Session session, String json) {
        GeneralWebSocketResponse response = GeneralWebSocketResponse.builder().rCode(GeneralWebSocketResponse.SUCCESS).build();
        int type = 0;
        try {
            StringWebSocketRequest request = JSON.parseObject(json, StringWebSocketRequest.class);
            type = request.getType();

            MessageType messageType = MessageType.getType(type);
            MessageHandler messageHandler = wsApplicationContext.getMessageHandlerMap().get(messageType);
            if (Objects.isNull(messageType) || Objects.isNull(messageHandler)) {
                log.warn("not find match messageType, sessionId=[{}], type=[{}], message=[{}]", session.getId(), type, json);
                return GeneralWebSocketResponse.builder().rCode(GeneralWebSocketResponse.FAIL).type(type).build();
            }


            if (!doApplyPre(session, request, response)) {
                return response;
            }

            response = messageHandler.handle(session, JSON.parseObject(request.getMessage(), messageType.getMessageClass()));

            response.setType(type);
            if (!doApplyPost(session, request, response)) {
                return response;
            }
            return response;
        } catch (Throwable throwable) {
            log.error("error :{}", throwable);
            return GeneralWebSocketResponse.builder().rCode(GeneralWebSocketResponse.FAIL).type(type).build();
        }

    }

    private boolean doApplyPost(Session session, StringWebSocketRequest request, GeneralWebSocketResponse response) {
        return interceptorChain.applyPost(session, request, response);
    }

    private boolean doApplyPre(Session session, StringWebSocketRequest request, GeneralWebSocketResponse response) {
        return interceptorChain.applyPre(session, request, response);
    }
}
