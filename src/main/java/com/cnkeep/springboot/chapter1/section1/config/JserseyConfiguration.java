package com.cnkeep.springboot.chapter1.section1.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * 描述~
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/18
 */
@Configuration
public class JserseyConfiguration extends ResourceConfig {
    public JserseyConfiguration() {

        packages("com.cnkeep.springboot.chapter1.section1");
    }
}
