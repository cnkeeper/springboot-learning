package com.cnkeep.springboot.chapter1.section1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/15
 */
@Controller
@RequestMapping(path = "hello")
public class HelloWorldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        LOGGER.info("info.....");
        LOGGER.warn("warnning....");
        LOGGER.error("error......");
        return "hello";
    }
}
