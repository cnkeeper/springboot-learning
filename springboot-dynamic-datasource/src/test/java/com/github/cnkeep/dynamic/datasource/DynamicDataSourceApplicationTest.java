package com.github.cnkeep.dynamic.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述: 启动入口
 * <pre>
 *     功能：测试动态数据源是否生效
 *     测试：
 *          1. 构建元数据库test_meta(存储各个数据源的配置信息)，导入：database_meta.sql;
 *          2. 构建相同数据库表的不同数据库test_01,test_02(用于模拟不同的数据源)，导入：database_dynamic.sql, database_dynamic_init.sql;
 *          3. 修改database_meta_init.sql中的配置数据源url信息，导入脚本
 *          4. 采用实现CommandLineRunner接口在程序启动后自动执行查询(代码中采用写死的数据源，未从数据库查询，原理是相通的，每次随机选择一个数据源来模拟动态数据源)
 *          5. 观察查询出来的数据是否是来自不同的数据库
 * </pre>
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/12
 */
@SpringBootApplication
public class DynamicDataSourceApplicationTest {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DynamicDataSourceApplicationTest.class);
        // 这里一个添加自定义文件，或者使用@ImportResource注解注入
        Set<String> resources = new HashSet<>();
        application.setSources(resources);

        application.run(args);
    }
}
