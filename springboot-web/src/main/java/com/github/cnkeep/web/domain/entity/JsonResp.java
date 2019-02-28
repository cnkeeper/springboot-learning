package com.github.cnkeep.web.domain.entity;

import lombok.Builder;

/**
 * 描述: 全局通用响应实体类
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/18
 */
@Builder
public class JsonResp {
    private int code;
    private Object data;
    private String message;
    private final static int FAIL_CODE = -1;
    private final static int SUCCESS_CODE = 0;


    public static JsonResp fail() {
        return JsonResp.builder().code(FAIL_CODE).build();
    }

    public static JsonResp fail(String message) {
        return JsonResp.builder()
                .code(FAIL_CODE)
                .message(message)
                .build();
    }
}
