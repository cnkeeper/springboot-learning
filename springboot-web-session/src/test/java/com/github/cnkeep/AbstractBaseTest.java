package com.github.cnkeep;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/13
 */
@RunWith(SpringRunner.class)
public class AbstractBaseTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
}
