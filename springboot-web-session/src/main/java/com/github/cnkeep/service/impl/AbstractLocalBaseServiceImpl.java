package com.github.cnkeep.service.impl;

import com.github.cnkeep.entity.Entity;
import com.github.cnkeep.service.BaseService;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * 描述: 本地存储的方式实现
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/7
 */
public abstract class AbstractLocalBaseServiceImpl<T extends Entity> implements BaseService<T> {
    private ConcurrentMap<Serializable,T> localStorage = new ConcurrentHashMap<>(16);
    @Override
    public List<T> list() {
        return localStorage.values().stream().collect(Collectors.toList());
    }

    @Override
    public T getById(Serializable id) {
        return localStorage.get(id);
    }

    @Override
    public T store(T t) {
        return localStorage.put(t.getId(),t);
    }

    @Override
    public T deleteById(Serializable id) {
        return localStorage.remove(id);
    }
}
