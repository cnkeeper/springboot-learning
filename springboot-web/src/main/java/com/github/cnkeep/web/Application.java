package com.github.cnkeep.web;


import com.github.cnkeep.web.listener.ApplicationContextInitListener;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashSet;
import java.util.Set;

/**
 * springboot 启动入口
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/15
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.github.cnkeep.web.*")
public class Application implements ApplicationContextAware {
    public static ApplicationContext applicationContext;

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(Application.class);
        // 这里一个添加自定义文件，或者使用@ImportResource注解注入
        Set<String> resources = new HashSet<>();
        application.setSources(resources);

        application.addInitializers(new ApplicationContextInitListener());
        application.run(args);

//        new SpringApplicationBuilder()
//                .sources(Parent.class)
//                .child(Application.class)
//                .bannerMode(Banner.Mode.CONSOLE)
//                .run(args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Application.applicationContext = applicationContext;
    }

}

