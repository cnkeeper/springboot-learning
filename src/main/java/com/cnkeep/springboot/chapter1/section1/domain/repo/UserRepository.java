package com.cnkeep.springboot.chapter1.section1.domain.repo;

import com.cnkeep.springboot.chapter1.section1.domain.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * 描述~
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/16
 */
@Repository
public class UserRepository {

    @Autowired
    @Qualifier("masterSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;


    public List<User> list(){
        List<User> userList = new LinkedList<User>();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        userList = sqlSession.selectList("com.cnkeep.springboot.chapter1.section1.mapper.UserMapper.list");
        return userList;
    }
}
