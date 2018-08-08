package com.cnkeep.springboot.chapter1.section1.config.swagger2;

import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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
@Configuration
public class Swagger2UIConfiguration {

    /**
     * 
     * @return
     */
    @Bean
    public ServletRegistrationBean registrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
//        servletRegistrationBean.setServlet(new SwaggerResourceServlet());
        servletRegistrationBean.setServlet(new swagger.http.servlet.SwaggerResourceServlet());
        servletRegistrationBean.addUrlMappings("/swagger2-ui/*");
        return servletRegistrationBean;
    }

    /**
     * 注册FilterRegistrationBean
     * @return
     */
//    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        bean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        return bean;
    }
}
