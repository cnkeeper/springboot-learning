package com.github.cnkeep.dynamic.datasource.service;

import com.github.cnkeep.dynamic.datasource.DataSourceProperty;

/**
 * 描述: 用户需自行实现该接口用户获取数据库基本信息
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/12
 */
public interface MetaDataSourceService {
    /**
     * 获取数据库配置信息
     * @return
     */
    DataSourceProperty fetchTargetDataSourceInfo();
}
