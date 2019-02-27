package com.github.cnkeep.web.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * shiro配置项
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/18
 */
@Configuration
@ConfigurationProperties("shiro")
public class ShiroConfig {
    /**
     * 登录url
     */
    private String loginUrl;
    /**
     * 是否开启验证码
     */
    private boolean loginVerificationCode;
    /**
     * 过滤器
     */
    private Map<String, String> filters;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public boolean isLoginVerificationCode() {
        return loginVerificationCode;
    }

    public void setLoginVerificationCode(boolean loginVerificationCode) {
        this.loginVerificationCode = loginVerificationCode;
    }

    public Map<String, String> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }
}
