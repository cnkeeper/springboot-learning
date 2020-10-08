package com.github.cnkeep.springboot.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 自动读取配置,采用@PropertySource指定配置文件路径的方式，此种方式可以适用于外部配置文件袋额导入，不在局限于application.yml
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/15
 */
@Configuration
@PropertySource(value = {"classpath:project.properties","classpath:project1.properties"})
@Order(value = 10)
public class CustomizeConfigurationByConfigurationProperty {
    /**
     * spring不支持静态属性的注入,因为静态属性属于类变量，而spring是基于对象的
     */
    public static String version;

    public CustomizeConfigurationByConfigurationProperty() {
        System.out.println(this.getClass());
    }

    /**
     * 依赖注入自定义的配置文件，不存在时默认值为hp
     **/
    @Value("${project.author:default}")
    public void setAuthor(String version) {
        System.out.println("------------@Value-----------");
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            System.out.println(stackTraceElement);
        }
        CustomizeConfigurationByConfigurationProperty.version = version;
    }

    @Bean(name = "testCondition")
    @ConditionalOnProperty(name = "project.author")
    public Object testCondition(){
        return new Object();
    }

    private class C1{}
    public class C2{}
    static class C3{}


    public static void main(String[] args) {
        Class<?>[] declaredClasses = CustomizeConfigurationByConfigurationProperty.class.getDeclaredClasses();
        System.out.println(Arrays.toString(declaredClasses));

        Field[] declaredFields = CustomizeConfigurationByConfigurationProperty.class.getDeclaredFields();
        System.out.println(Arrays.toString(declaredFields));
    }
}
