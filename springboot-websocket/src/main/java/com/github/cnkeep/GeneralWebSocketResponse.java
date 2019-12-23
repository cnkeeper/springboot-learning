package com.github.cnkeep;

import lombok.Builder;
import lombok.Data;

/**
 * @description: websocket Response通用结构
 * @author: <a href="mailto:zhangleili@lizhi.fm">LeiLi.Zhang</a>
 * @date: 2019-09-04
 * @version: V1.1.8
 **/
@Data
@Builder
public class GeneralWebSocketResponse<T> {
	public static final int SUCCESS = 0;
	public static final int FAIL = -1;
	public static final int TOKEN_EXPIRED = 1001;
	/**
     * 响应码
	 */
	private int rCode = SUCCESS;
	/**
     * 业务场景
	 */
	private int type;
	/**
     * 错误消息，用于提示出错详情
	 */
	private String errorMessage;

    /**
     * 响应体
	 */
	private T message;
}
