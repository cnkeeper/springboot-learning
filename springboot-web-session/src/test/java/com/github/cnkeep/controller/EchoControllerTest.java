package com.github.cnkeep.controller;

import com.github.cnkeep.AbstractBaseWebMvcTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 描述: 测试
 *
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date ${DATE}
 */

public class EchoControllerTest extends AbstractBaseWebMvcTest {

    @Test
    public void echoTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/info")
                                                                            .param("word", "hello")
                                                                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                                                                            .accept(MediaType.APPLICATION_JSON_UTF8);

        String contentAsString = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(contentAsString);
    }
}