package com.cnkeep.springboot.chapter1.section1.config.security;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import com.cnkeep.springboot.chapter1.section1.security.ShiroRealm;
import com.cnkeep.springboot.chapter1.section1.security.filter.ShiroAnonymousFilter;
import com.cnkeep.springboot.chapter1.section1.security.filter.ShiroAuthorizationFilter;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @description shiro配置管理器
 * @date 2018年1月24日
 * <section style="border:1px solid">
 * <span>web.xml</span>
 * <xmp >
 * <filter>
 * <filter-name>shiroFilter</filter-name>
 * <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
 * </filter>
 * <filter-mapping>
 * <filter-name>shiroFilter</filter-name>
 * <url-pattern>/*</url-pattern>
 * </filter-mapping>
 * </xmp>
 * </section>
 * <section style="border:1px solid">
 * <span>spring-context.xml<span>
 * <xmp>
 * <!-- 定义Shiro安全管理配置 -->
 * <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
 * <property name="realm" ref="systemAuthorizingRealm" />
 * <property name="sessionManager" ref="sessionManager" />
 * <property name="cacheManager" ref="shiroCacheManager" />
 * </bean>
 * <p>
 * <!-- 安全认证过滤器 -->
 * <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
 * <property name="securityManager" ref="securityManager" /><!--
 * <property name="loginUrl" value="${cas.server.url}?service=${cas.project.url}${adminPath}/cas" /> -->
 * <property name="loginUrl" value="${adminPath}/login" />
 * <property name="successUrl" value="${adminPath}?login" />
 * <property name="filters">
 * <map>
 * <entry key="cas" value-ref="casFilter"/>
 * <entry key="authc" value-ref="formAuthenticationFilter"/>
 * </map>
 * </property>
 * <property name="filterChainDefinitions">
 * <ref bean="shiroFilterChainDefinitions"/>
 * </property>
 * </bean>
 * <p>
 * <!-- 自定义会话管理配置 -->
 * <bean id="sessionManager" class="com.yonyou.hotusm.common.security.session.SessionManager">
 * <property name="sessionDAO" ref="sessionDAO"/>
 * <!-- 会话超时时间，单位：毫秒  -->
 * <property name="globalSessionTimeout" value="${session.sessionTimeout}"/>
 * <!-- 定时清理失效会话, 清理用户直接关闭浏览器造成的孤立会话   -->
 * <property name="sessionValidationInterval" value="${session.sessionTimeoutClean}"/>
 * <property name="sessionValidationSchedulerEnabled" value="true"/>
 * <property name="sessionIdCookie" ref="sessionIdCookie"/>
 * <property name="sessionIdCookieEnabled" value="true"/>
 * </bean>
 * <p>
 * <!-- 保证实现了Shiro内部lifecycle函数的bean执行 -->
 * <bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor"/>
 * </xmp>
 * </section>
 */
@Configuration
public class ShiroConfiguration {
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }

    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public ShiroRealm shiroRealm() {
        return new ShiroRealm();
    }

    @Bean
    public EhCacheManager cacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return cacheManager;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        SimpleCookie cookie = new SimpleCookie("ZHIYUN_SESSIONID");
        cookie.setHttpOnly(true);
        sessionManager.setSessionIdCookie(cookie);
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(ShiroRealm realm, CacheManager cacheManager,
                                                     SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealm(realm);
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager,
                                                            ShiroConfig config) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl(config.getLoginUrl());
        shiroFilterFactoryBean.setUnauthorizedUrl("/common/unauthorized");
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        // TODO 配置自定义的权限过滤器
        filters.put("authorization", new ShiroAuthorizationFilter());
        filters.put("anonymous", new ShiroAnonymousFilter());
        shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(config.getFilters());
        return shiroFilterFactoryBean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
