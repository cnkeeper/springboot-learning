package com.github.cnkeep;

import lombok.Data;

/**
 * @description: websocket通用请求体
 * @author: <a href="mailto:zhangleili@lizhi.fm">LeiLi.Zhang</a>
 * @date: 2019-09-03
 * @version: v1.1.8
 **/
@Data
public class GeneralWebSocketRequest<T> {
    /**
     * 消息类型，用于区分相同业务场景下的不同消息
     */
    private int type;

    /**
     * 请求数据
     */
    private T message;
}
