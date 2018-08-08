package com.cnkeep.springboot.chapter1.section1.config.customize;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 自动读取配置,采用@PropertySource指定配置文件路径的方式，此种方式可以适用于外部配置文件袋额导入，不在局限于application.yml
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/15
 */
@Configuration
@ConfigurationProperties(prefix = "project")
public class CustomizeConfigurationByPropertySource {
    /*spring不支持静态属性的注入,因为静态属性属于类变量，而spring是基于对象的*/
    public static String author;

    /*依赖注入自定义的配置文件，不存在时默认值为hp*/
    //@Value("${project.author:hp}")
    public void setAuthor(String author) {
        CustomizeConfigurationByPropertySource.author = author;
    }
}
