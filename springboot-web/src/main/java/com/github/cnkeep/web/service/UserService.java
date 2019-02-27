package com.github.cnkeep.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述~
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/11/22
 */
@Service
public class UserService implements UserServiceInterface {
    public void updateUser(){
    }

    @Override
    @Transactional(rollbackFor = Exception.class,transactionManager = "masterTransactionManager")
    public void transaction() {
        throw new RuntimeException("Transactional test");
    }
}
