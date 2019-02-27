package com.github.cnkeep.web;


import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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

