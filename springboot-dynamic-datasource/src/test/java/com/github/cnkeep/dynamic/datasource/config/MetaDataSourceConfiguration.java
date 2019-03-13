package com.github.cnkeep.dynamic.datasource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 描述: 元数据源配置 </br>
 * <pre>
 *   元数据库中存储着其他数据源的配置信息
 * </pre>
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/12
 */
@Configuration
public class MetaDataSourceConfiguration {
    @Autowired
    private MetaDataSourceProperties config;

    @Bean("metaDataSource")
    public DataSource masterDataSource() {
        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        dataSource.setDriverClassName(config.getDriver());
        dataSource.setJdbcUrl(config.getUrl());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        return dataSource;
    }

    @Bean(name = "metaTransactionManager")
    public DataSourceTransactionManager gsmsTransactionManager(@Qualifier("metaDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
