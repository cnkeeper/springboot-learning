package com.github.cnkeep.dynamic.datasource.config.datasource;

import com.github.cnkeep.dynamic.datasource.AbstractDynamicDataSource;
import com.github.cnkeep.dynamic.datasource.DataSourceProperty;
import com.github.cnkeep.dynamic.datasource.service.MetaDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/13
 */
@Component
public class DynamicDataSource extends AbstractDynamicDataSource {

    @Autowired
    private MetaDataSourceService metaDataSourceService;

    @Override
    protected DataSourceProperty obtainTargetDataSourceProperty() {
        return metaDataSourceService.fetchTargetDataSourceInfo();
    }
}
