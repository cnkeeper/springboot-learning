package com.github.cnkeep.controller;

import com.github.cnkeep.entity.User;
import com.github.cnkeep.resp.JsonResponse;
import com.github.cnkeep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.io.Serializable;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/7
 */
@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping
    public JsonResponse register(@RequestBody @Validated User user) {
        return JsonResponse.success(userService.store(user));
    }

    /**
     * 用户删除
     * @param userId
     * @return
     */
    @DeleteMapping("/{id}")
    public JsonResponse delUser(@PathParam("id") @NotNull Serializable userId){
        return JsonResponse.success(userService.deleteById(userId));
    }

    /**
     * 用户查询
     * @param userId
     * @return
     */
    @GetMapping("/{id}")
    public JsonResponse findUser(@PathParam("id") @NotNull Serializable userId){
        return JsonResponse.success(userService.getById(userId));
    }

    /**
     * 用户修改
     * @param user
     * @return
     */
    @PutMapping
    public JsonResponse modify(@RequestBody @Validated User user) {
        return JsonResponse.success(userService.store(user));
    }

}
