package com.github.cnkeep.service;

import com.github.cnkeep.AbstractBaseWebMvcTest;
import com.github.cnkeep.exception.TransactionalExecption;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * 描述: 事务是否生效测试
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/8
 */
public class UserServiceTest extends AbstractBaseWebMvcTest {
    @Autowired
    private UserService userService;


    /**
     * 测试事务，当抛出指定异常时则通过测试
     */
    @Test(expected = TransactionalExecption.class)
    @Rollback
    public void transactionalTest1() {
        userService.transactionalOperation();
    }

    @Test
    @Rollback
    public void transactionalTest() {
        //更加精细化测试异常，可以指定异常信息是否符合
        thrown.expect(TransactionalExecption.class);
        thrown.expectMessage("Transaction test!!!");
        userService.transactionalOperation();
    }
}
