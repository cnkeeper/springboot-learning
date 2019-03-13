package com.github.cnkeep.dynamic.datasource.service.impl;

import com.github.cnkeep.dynamic.datasource.DataSourceProperty;
import com.github.cnkeep.dynamic.datasource.service.MetaDataSourceService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/12
 */
@Service
public class MetaDataSourceServiceImpl implements MetaDataSourceService {
    /*模拟多态数据源*/
    private static String[] meta_data_source_info = {
            "jdbc:mysql://172.16.22.135:3306/test_01?useSSL=false&autoReconnect=true&sessionVariables=FOREIGN_KEY_CHECKS=0&useUnicode=yes&characterEncoding=UTF-8",
            "jdbc:mysql://172.16.22.135:3306/test_02?useSSL=false&autoReconnect=true&sessionVariables=FOREIGN_KEY_CHECKS=0&useUnicode=yes&characterEncoding=UTF-8"
    };

    @Override
    public DataSourceProperty fetchTargetDataSourceInfo() {
        // 1. 获取当前用户信息，SessionUtil.getCurrentUser()
        // 2. 查询元数据库，获取数据库基础配置信息
        DataSourceProperty defaultDataSourceProperty = new DataSourceProperty();
        // 随机选择数据源
        Random random = new Random();
        defaultDataSourceProperty.setUrl(meta_data_source_info[random.nextInt(10)%2]);
        defaultDataSourceProperty.setDriverClassName("com.mysql.cj.jdbc.Driver");
        defaultDataSourceProperty.setUsername("root");
        defaultDataSourceProperty.setPassword("123456");
        return defaultDataSourceProperty;
    }
}
