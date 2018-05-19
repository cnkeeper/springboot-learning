package com.cnkeep.springboot.chapter1.section1.domain.entity;

/**
 * 描述~
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/18
 */
public abstract class BaseEntity implements Entity{
    private String extraAttr;

    public String getExtraAttr() {
        return extraAttr;
    }

    public void setExtraAttr(String extraAttr) {
        this.extraAttr = extraAttr;
    }
}
