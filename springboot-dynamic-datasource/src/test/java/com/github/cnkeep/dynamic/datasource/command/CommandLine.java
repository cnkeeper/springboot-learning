package com.github.cnkeep.dynamic.datasource.command;

import com.github.cnkeep.dynamic.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/12
 */
@Component
public class CommandLine implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        for(int count=0;count<10;count++) {
            userService.findUserById(1);
        }
    }
}
