package com.github.cnkeep.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 描述: 用户登录拦截器
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/11
 */
public class LoginFilter implements Filter {
    /*标识用户已经登录*/
    public static final String LOGIN_FLAG_KEY = "Login";
    private static final String DEFAULT_LOGIN_URL = "/login";

    @Autowired
    private ServerProperties serverProperties;
    @Autowired
    private WebMvcProperties webMvcProperties;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>.");
        if(request instanceof HttpServletRequest){
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
            Optional.ofNullable(cookies).ifPresent(c->Stream.of(c).forEach(System.out::println));
            boolean skipFilter = skipFilter(httpServletRequest.getRequestURL());
            if(skipFilter){
                chain.doFilter(request,response);
                return;
            }
            HttpSession session = httpServletRequest.getSession();
            if(null != session && null!=session.getAttribute(LOGIN_FLAG_KEY)){
                chain.doFilter(request,response);
                return;
            }
        }
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader(LOGIN_FLAG_KEY,"NOT_LOGIN");
        httpServletResponse.sendRedirect(getLoginWholeUrl());
    }

    private boolean skipFilter(StringBuffer requestURL) {
        System.out.println("requestUrl:"+requestURL);
        return requestURL.toString().contains(getLoginUrl());
    }

    private String getLoginWholeUrl(){
        return serverProperties.getServlet().getContextPath()+DEFAULT_LOGIN_URL+webMvcProperties.getView().getSuffix();
    }

    private String getLoginUrl(){
        return DEFAULT_LOGIN_URL;
    }

    public void login(HttpServletRequest request){
        request.getSession().setAttribute(LOGIN_FLAG_KEY,request.getRequestURL());
    }

    public void loginOut(HttpServletRequest request){
        request.getSession().invalidate();
    }
}
