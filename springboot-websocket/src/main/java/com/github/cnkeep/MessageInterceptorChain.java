package com.github.cnkeep;



import com.github.cnkeep.util.CollectionUtils;

import javax.websocket.Session;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 拦截器链
 * @author: <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @date: 2019-09-05
 * @version: v1.1.8
 **/
public class MessageInterceptorChain {
    private List<MessageInterceptor> interceptors = new LinkedList<>();

    /**
     * 前置处理
     *
     * @param session
     * @param request
     * @param response
     * @return
     */
    public boolean applyPre(Session session, GeneralWebSocketRequest request, GeneralWebSocketResponse response) {
        if (!CollectionUtils.isEmpty(interceptors)) {
            int size = interceptors.size();
            for (int index = 0; index < size; index++) {
                MessageInterceptor interceptor = interceptors.get(index);
                if (!interceptor.pre(session, request, response)) {
                    return false;
                }
            }

            return true;
        }

        return true;
    }

    /**
     * 后置处理
     *
     * @param session
     * @param request
     * @param response
     * @return
     */
    public boolean applyPost(Session session, GeneralWebSocketRequest request, GeneralWebSocketResponse response) {
        if (!CollectionUtils.isEmpty(interceptors)) {
            int size = interceptors.size();
            for (int index = 0; index < size; index++) {
                MessageInterceptor interceptor = interceptors.get(index);
                if (!interceptor.post(session, request, response)) {
                    return false;
                }
            }

            return true;
        }

        return true;
    }

    public List<MessageInterceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<MessageInterceptor> interceptors) {
        this.interceptors = interceptors;
    }
}
