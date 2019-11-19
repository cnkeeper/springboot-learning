package com.github.cnkeep.common.util.designmodel.strategy;

/**
 * @description: 处理器工厂类
 * @author: <a href="mailto:zhangleili@lizhi.fm">LeiLi.Zhang</a>
 * @date: 2019-11-19
 * @version: v1.0.0
 **/
public interface HandlerFactory<T, H> {
    /**
     * 注册处理器
     *
     * @param key
     * @param handler
     */
    void register(T key, H handler);

    /**
     * 获取对应的处理器
     *
     * @param type
     * @return
     */
    H getHandler(T type);
}
