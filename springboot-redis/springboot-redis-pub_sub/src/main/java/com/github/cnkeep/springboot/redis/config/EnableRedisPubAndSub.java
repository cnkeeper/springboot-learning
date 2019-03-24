package com.github.cnkeep.springboot.redis.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/24
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Import(RedisPubAndSubAutoConfiguration.class)
public @interface EnableRedisPubAndSub {
}
