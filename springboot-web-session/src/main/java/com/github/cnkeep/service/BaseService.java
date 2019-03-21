package com.github.cnkeep.service;

import com.github.cnkeep.entity.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * 描述: 封装通用的业务接口
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/7
 */
public interface BaseService<T extends Entity> {
    /**
     * 查找列表
     * @return
     */
    List<T> list();

    /**
     * 通过id查找
     * @param id
     * @return
     */
    T getById(Serializable id);

    /**
     * 添加
     * @param t
     * @return
     */
    T store(T t);

    /**
     * 删除
     * @param id
     * @return
     */
    T deleteById(Serializable id);
}
