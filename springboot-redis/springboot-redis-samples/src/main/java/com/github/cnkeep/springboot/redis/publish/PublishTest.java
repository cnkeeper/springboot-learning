package com.github.cnkeep.springboot.redis.publish;

import com.github.cnkeep.springboot.redis.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PublishTest {

    @Autowired
    private BusinessPublisher businessPublisher;
    @PostConstruct
    public void init(){
        businessPublisher.publish(new RedisLock());
    }
}
