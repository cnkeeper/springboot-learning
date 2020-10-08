package com.github.cnkeep.springboot.redis.publish;

import com.github.cnkeep.springboot.redis.lock.RedisLock;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/22
 */
@Component
public class PublishTest implements ApplicationContextAware, InitializingBean {
    @Value("${project.author:default}")
    public String version;

    @Value("${project.line}")
    public Integer line;

    private BusinessPublisher businessPublisher;
    @PostConstruct
    public void init(){
        businessPublisher.publish(new RedisLock());
    }

    public PublishTest() {
        System.out.println(this.getClass());
    }

    public void test(){
        this.businessPublisher.publish("");
    }

    @Autowired
    public void setBusinessPublisher(BusinessPublisher businessPublisher) {
        this.businessPublisher = businessPublisher;
//        new RuntimeException("Test");
        System.out.println("Setting.............");
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            System.out.println(stackTraceElement.toString());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext.............");
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            System.out.println(stackTraceElement.toString());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this.version);
        System.out.println(line);

    }
}
