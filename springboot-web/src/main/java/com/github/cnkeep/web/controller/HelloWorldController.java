package com.github.cnkeep.web.controller;

import com.github.cnkeep.web.domain.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 测试 springmvc
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/15
 */
@RestController
@RequestMapping(path = "hello")
public class HelloWorldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping(path = "/test", method = RequestMethod.POST)
    @GetMapping(path = "test",consumes= MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User hello(@RequestBody @Valid User user) {
        LOGGER.info("info.....");
        LOGGER.warn("warnning....");
        LOGGER.error("error......");
        return user;
    }
}
