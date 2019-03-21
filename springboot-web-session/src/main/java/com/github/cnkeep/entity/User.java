package com.github.cnkeep.entity;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 描述: 用户实体
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/7
 */
@Data
public class User implements Entity{
    private Integer id;
    @Min(value = 0, message = "不能小于0!")
    @Max(value = 200, message = "不能大于200!")
    private int age;

    @NotEmpty(message = "用户名不能为空!")
    private String name;

    private String password;

    @Pattern(regexp = "1[0-9]{10}", message = "手机号码不正确!")
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
