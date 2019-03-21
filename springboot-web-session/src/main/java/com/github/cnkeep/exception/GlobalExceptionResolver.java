package com.github.cnkeep.exception;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/6
 */

import com.github.cnkeep.resp.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局Controller层异常处理类
 */
@ControllerAdvice
public class GlobalExceptionResolver {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    /**
     * 处理所有不可知异常 * * @param e 异常 * @return json结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResponse handleException(Exception e) {
        // 打印异常堆栈信息
        LOG.error("error cause by:",e);
        return JsonResponse.fail(e.getMessage());
    }
}