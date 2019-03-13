package com.github.cnkeep.dynamic.datasource;

import javax.sql.DataSource;

/**
 * 描述: 动态DataSource
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/12
 */
public abstract class AbstractDynamicDataSource extends DelegatingDataSource {
    /**
     * 获取目标数据库的配置信息, 子类需实现改方法
     *
     * @return DataSourceProperty
     */
    protected abstract DataSourceProperty obtainTargetDataSourceProperty();

    @Override
    public DataSource getTargetDataSource() {
        DataSourceProperty dataSourceProperty = obtainTargetDataSourceProperty();

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create()
                .driverClassName(dataSourceProperty.getDriverClassName())
                .url(dataSourceProperty.getUrl())
                .username(dataSourceProperty.getUsername())
                .password(dataSourceProperty.getPassword());
        return dataSourceBuilder.build();
    }
}
