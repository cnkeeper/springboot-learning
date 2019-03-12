package com.github.cnkeep.web.domain.entity.DO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述~
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/18
 */
public abstract class BaseEntity implements Entity {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expired_in")
    private long expiredIn;

    private String extraAttr;

    public String getExtraAttr() {
        return extraAttr;
    }

    public void setExtraAttr(String extraAttr) {
        this.extraAttr = extraAttr;
    }
}
