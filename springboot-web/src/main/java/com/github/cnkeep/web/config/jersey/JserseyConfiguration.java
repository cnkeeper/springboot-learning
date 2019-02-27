package com.github.cnkeep.web.config.jersey;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * 描述~
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/18
 */
//@Configuration
public class JserseyConfiguration extends ResourceConfig {
    public JserseyConfiguration() {
        packages("com.github.cnkeep.web.resource");
    }
}
