package com.github.cnkeep.springboot.redis.util;

import java.util.concurrent.TimeUnit;

/**
 * 描述~
 *
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2019/2/27
 */
public interface DistributeLock{
    void lock(String key, long timeout, TimeUnit timeUnit);
    boolean tryLock(String key, long timeout, TimeUnit timeUnit);
    boolean release(String key);
}
