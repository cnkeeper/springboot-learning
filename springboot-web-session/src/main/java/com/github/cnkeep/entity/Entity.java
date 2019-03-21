package com.github.cnkeep.entity;

import java.io.Serializable;

/**
 * 描述: 基础实体接口，所有实体对象必须实现该接口
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/7
 */
@FunctionalInterface
public interface Entity extends Serializable {
    Serializable getId();
}
