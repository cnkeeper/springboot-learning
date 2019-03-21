package com.github.cnkeep.controller;

import com.github.cnkeep.entity.User;
import com.github.cnkeep.filter.LoginFilter;
import com.github.cnkeep.resp.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/11
 */
@RestController
public class LoginController {

    @Autowired
    private LoginFilter loginFilter;

    @PostMapping(path = "/login")
    public JsonResponse login(User user, HttpServletRequest request) throws ServletException {
        if (user.getName().equals(user.getPassword())){
            loginFilter.login(request);
            return JsonResponse.success();
        }
        return JsonResponse.fail();
    }

    @GetMapping(path = "/loginout")
    public JsonResponse loginOut(HttpServletRequest request){
        loginFilter.loginOut(request);
        return JsonResponse.success();
    }
}
