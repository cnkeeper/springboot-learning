package com.github.cnkeep.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/3
 */
public class ApplicationContextInitListener implements ApplicationContextInitializer {
    /**
    * Logger
    * */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextInitListener.class);
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ApplicationContextInitializer----->>initialize...........");
    }
}
