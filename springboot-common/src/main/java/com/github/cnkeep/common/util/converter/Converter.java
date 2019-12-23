package com.github.cnkeep.common.util.converter;

/**
 * @description: 实体转换接口
 * @author: <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @date: 2019-11-19
 * @version: v1.0.0
 **/
public interface Converter<S, T> {

    /**
     * 实体转换
     * @param source
     * @return
     */
    T convert(S source);
}
