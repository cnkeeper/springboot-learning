package com.github.cnkeep.handler;

import com.github.cnkeep.GeneralWebSocketResponse;
import com.github.cnkeep.MessageHandler;
import com.github.cnkeep.MessageType;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

/**
 * @description: 空处理器
 * @author: <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @date: 2019-09-04
 * @version: v1.1.8
 **/
@Component
public class NullMessageHandler implements MessageHandler<String> {
    @Override
    public GeneralWebSocketResponse handle(Session session, String message) {
        // do nothing
        return GeneralWebSocketResponse.builder().rCode(GeneralWebSocketResponse.SUCCESS).build();
    }

    @Override
    public MessageType acceptType() {
        return MessageType.NULL;
    }
}
