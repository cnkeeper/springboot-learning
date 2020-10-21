package com.github.cnkeep.springboot.redis.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LoggerAspect {

    public LoggerAspect() {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            System.out.println("===================");
        }
    }

    @Pointcut("execution( * com.github.cnkeep.springboot.redis..*.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void logPointCut() {
    }

    @After("logPointCut()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("method={}#{}, args={}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    @Before("logPointCut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("method={}#{}, args={}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }
}
