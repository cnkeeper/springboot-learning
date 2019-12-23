package com.github.cnkeep.interceptor;

import com.github.cnkeep.GeneralWebSocketRequest;
import com.github.cnkeep.GeneralWebSocketResponse;
import com.github.cnkeep.MessageInterceptor;
import com.github.cnkeep.StringWebSocketRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

/**
 * @description: token校验
 * @author: <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @date: 2019-09-16
 * @version: v1.1.8
 **/
@Component
@Slf4j
public class TokenInterceptor implements MessageInterceptor {

    @Override
    public boolean pre(Session session, GeneralWebSocketRequest request, GeneralWebSocketResponse response) {
        if(request instanceof StringWebSocketRequest){
            try {
               // TODO
            }catch (Exception e){
                log.error("error to check token, with error{}",e);
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean post(Session session, GeneralWebSocketRequest request, GeneralWebSocketResponse response) {
        return true;
    }
}
