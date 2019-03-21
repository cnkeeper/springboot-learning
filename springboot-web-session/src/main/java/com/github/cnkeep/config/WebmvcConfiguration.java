package com.github.cnkeep.config;

import com.github.cnkeep.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.List;

/**
 * 描述: web自定义配置
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/6
 */
@Configuration
class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Bean
    public FilterRegistrationBean loginFilterRegisterBean(){
        FilterRegistrationBean<Filter> loginFilter = new FilterRegistrationBean<>();
        loginFilter.setFilter(loginFilter());
        loginFilter.addUrlPatterns("/*");
        loginFilter.setOrder(0);
        loginFilter.setName("loginFilter");
        System.out.println("option...........");
        return loginFilter;
    }

    @Bean
    public Filter loginFilter(){
        return new LoginFilter();
    }
}
