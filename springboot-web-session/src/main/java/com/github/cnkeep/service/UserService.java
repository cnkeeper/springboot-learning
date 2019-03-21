package com.github.cnkeep.service;

import com.github.cnkeep.entity.User;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/7
 */
public interface UserService extends BaseService<User>{
    /**
     * 事务操作
     */
    void transactionalOperation();
}
