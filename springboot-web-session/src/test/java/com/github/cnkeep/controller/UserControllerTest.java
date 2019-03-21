package com.github.cnkeep.controller;

import com.github.cnkeep.AbstractBaseWebMvcTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 描述： 用户相关Rest接口测试
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date ${DATE}
 */
public class UserControllerTest extends AbstractBaseWebMvcTest {
    @Before
    public void setUp() throws Exception {
        System.out.println("Before...........");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("After............");
    }

    @Test
    public void register() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "  \"id\":1001,\n" +
                        "  \"name\":\"张三\",\n" +
                        "  \"phone\":\"18829042246\",\n" +
                        "  \"age\":100\n" +
                        "}");
        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();
        String contentAsString = response.getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void delUser() throws Exception {
    }

    @Test
    public void findUser() throws Exception {
    }

    @Test
    public void modify() throws Exception {
    }

}