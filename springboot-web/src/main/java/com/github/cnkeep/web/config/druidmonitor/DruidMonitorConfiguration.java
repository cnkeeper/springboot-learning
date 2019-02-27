package com.github.cnkeep.web.config.druidmonitor;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 描述: Druid 数据库连接池监控页面配置
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/8/8
 */
@Configuration
public class DruidMonitorConfiguration {

    @NotNull
    @NotEmpty
    @Value("${druid.monitor.servlet-path:/druid-ui/*}")
    private String servletPath;

    @NotNull
    @NotEmpty
    @Value("${druid.monitor.username}")
    private String userName;

    @NotNull
    @NotEmpty
    @Value("${druid.monitor.password}")
    private String password;


    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings(servletPath);
        reg.addInitParameter("loginUsername", userName);
        reg.addInitParameter("loginPassword", userName);
        return reg;
    }

    @Bean(name = "druidFilter")
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,"+servletPath);
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        return filterRegistrationBean;
    }
}
