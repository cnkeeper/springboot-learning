package com.github.cnkeep.common.util.designmodel.strategy;

/**
 * @description:
 * @author: <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @date: 2019-11-19
 * @version:
 **/
public interface Handler<T, A> {
    /**
     * 处理逻辑
     *
     * @param args
     * @return
     */
    int handle(A args);

    /**
     * 返回支持的处理类型，推荐使用枚举
     *
     * @return 支持的类型
     */
    T accept();
}
