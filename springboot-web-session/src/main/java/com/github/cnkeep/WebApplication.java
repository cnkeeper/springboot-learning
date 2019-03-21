package com.github.cnkeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述： 启动入口
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/6
 */
@SpringBootApplication
@EnableTransactionManagement
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WebApplication.class);
        Set<String> set = new HashSet<>();
        app.setSources(set);
        app.run(args);
    }
}
