package com.github.cnkeep.resp;

import lombok.Builder;
import lombok.Data;

/**
 * 描述: 统一响应对象
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/6
 */
@Builder
@Data
public class JsonResponse{
    private int code;
    private Object data;
    private String message;
    private final static int FAIL_CODE = -1;
    private final static int SUCCESS_CODE = 0;


    public static JsonResponse fail() {
        return JsonResponse.builder().code(FAIL_CODE).build();
    }

    public static JsonResponse fail(String message) {
        return JsonResponse.builder()
                .code(FAIL_CODE)
                .message(message)
                .build();
    }

    public static JsonResponse success() {
        return JsonResponse.builder().code(SUCCESS_CODE).build();
    }

    public static JsonResponse success(Object data) {
        return JsonResponse.builder()
                .code(SUCCESS_CODE)
                .data(data)
                .build();
    }
}

