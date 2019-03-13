package com.github.cnkeep.dynamic.datasource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/12
 */
@Configuration
public class JdbcTemplateConfiguration {
    @Autowired
    @Qualifier("dynamicDataSource")
    private DataSource dynamicDataSource;

    /**
     * 动态数据源的JdbcTemplate
     * @return
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dynamicDataSource);
        return jdbcTemplate;
    }
}
