package com.cnkeep.springboot.chapter1.section1.config.swagger2;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述: swagger-ui configuration
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/8/8
 */
//@Configuration
public class JerseySwagger2UIConfiguration {

    /**
     * 
     * @return
     */
//    @Bean
    public ServletRegistrationBean registrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
//        servletRegistrationBean.setServlet(new SwaggerResourceServlet());
        servletRegistrationBean.setServlet(new swagger.http.servlet.SwaggerResourceServlet());
        servletRegistrationBean.addUrlMappings("/swagger2-ui/*");
        return servletRegistrationBean;
    }
}
