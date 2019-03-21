package com.github.cnkeep.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述: SpringSession 接口
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018-11-23
 */
@RestController
@RequestMapping(value = "/session")
public class SpringSessionController {

    @PostMapping
    public Map<String, Object> addSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(8);
        HttpSession session = request.getSession();
        session.setAttribute("request Url", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(8);
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        Object sessionMap = session.getAttribute("map");
        map.put("sessionId", sessionId);
        map.put("message", sessionMap);
        return map;
    }
} 