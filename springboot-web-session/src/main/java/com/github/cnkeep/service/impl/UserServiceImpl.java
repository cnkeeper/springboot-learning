package com.github.cnkeep.service.impl;

import com.github.cnkeep.entity.User;
import com.github.cnkeep.exception.TransactionalExecption;
import com.github.cnkeep.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述: 用户处理业务层
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/7
 */
@Service
public class UserServiceImpl extends AbstractLocalBaseServiceImpl<User> implements UserService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transactionalOperation() {
        throw new TransactionalExecption("Transaction test!!!");
    }
}
