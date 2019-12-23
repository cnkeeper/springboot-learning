package com.github.cnkeep.common.util.designmodel.strategy;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @description: AbstractBaseHandlerFactory
 * @author: <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @date: 2019-11-19
 * @version: v1.0.0
 **/
public abstract class AbstractBaseHandlerFactory<T, H> implements HandlerFactory<T, H> {
    private final ConcurrentMap<T, H> HANDLERS_MAP = new ConcurrentHashMap<>();

    /**
     * 子类注册handler接口
     * <pre>
     *
     * </pre>
     */
    protected abstract void registerHandler();

    @Override
    public void register(T type, H handler) {
        Objects.requireNonNull(type, "type can not be null!");
        Objects.requireNonNull(handler, "com.github.cnkeep.handler can not be null!");
        HANDLERS_MAP.put(type, handler);
    }

    @Override
    public H getHandler(T type) {
        return HANDLERS_MAP.get(type);
    }
}
