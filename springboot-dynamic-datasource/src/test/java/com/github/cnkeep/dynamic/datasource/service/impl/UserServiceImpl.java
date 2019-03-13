package com.github.cnkeep.dynamic.datasource.service.impl;

import com.github.cnkeep.dynamic.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

/**
 * 描述： 用户相关业务处理层
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/12
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void findUserById(Integer id) {
        jdbcTemplate.query("select * from user where id="+id, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                int id = rs.getInt(1);
                String userName = rs.getString(2);
                System.out.println(MessageFormat.format("Result=> id={0}, userName={1}.",id,userName));
            }
        });
    }
}
