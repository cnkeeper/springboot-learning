package com.github.cnkeep.web.config.jersey;

import com.github.cnkeep.web.domain.entity.VO.JsonResp;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * 描述: 全局异常处理器
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/2/28
 */
public class GlocalExceptionMapper implements ExceptionMapper {
    @Override
    public Response toResponse(Throwable exception) {
        return commonResponse(exception);
    }

    /**
     * 通用错误响应
     */
    public Response commonResponse(Throwable e) {
        return Response.ok()
                .entity(JsonResp.fail(e.getMessage()))
                .build();
    }
}
