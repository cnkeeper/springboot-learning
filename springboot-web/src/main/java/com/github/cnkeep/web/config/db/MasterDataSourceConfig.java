package com.github.cnkeep.web.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 描述~
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/16
 */
@Configuration
public class MasterDataSourceConfig {
    @Value("${mybatis.config-location}")
    private String mybatisConfigLocation;

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;
    @Autowired
    private MasterDsConfig config;

    @Bean("masterDataSource")
    public DataSource masterDataSource() {
        DruidDataSource dataSource = (DruidDataSource) DataSourceBuilder.create().type(DruidDataSource.class).build();
        dataSource.setDriverClassName(config.getDriver());
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        dataSource.setMaxActive(config.getMaxTotal());
        dataSource.setMaxIdle(config.getMaxIdle());
        dataSource.setMinIdle(config.getMinIdle());
        dataSource.setInitialSize(config.getInitialSize());
        dataSource.setValidationQuery(config.getValidationQuery());
        dataSource.setTestWhileIdle(config.isTestWhileIdle());
        dataSource.setTestOnBorrow(config.isTestOnBorrow());
        dataSource.setTestOnReturn(config.isTestOnReturn());
        dataSource.setMaxWait(config.getMaxWaitMillis());
        dataSource.setTimeBetweenEvictionRunsMillis(config.getTimeBetweenEvictionRunsMillis());
        dataSource.setNumTestsPerEvictionRun(config.getNumTestsPerEvictionRun());
        dataSource.setMinEvictableIdleTimeMillis(config.getMinEvictableIdleTimeMillis());
        dataSource.setDefaultAutoCommit(false);
        return dataSource;
    }

    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager gsmsTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource)
            throws Exception {
        String path = mybatisConfigLocation.replace("classpath:", "/");
        ClassPathResource resource = new ClassPathResource(path);

        Interceptor[] plugins = {};

        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setConfigLocation(resource);
        factory.setPlugins(plugins);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources(mapperLocations));
        return factory.getObject();
    }
}
