package com.cnkeep.springboot.test.chapter1.section1;

import com.cnkeep.springboot.chapter1.section1.Application;
import com.cnkeep.springboot.chapter1.section1.config.CustomizeConfigurationByConfigurationProperty;
import com.cnkeep.springboot.chapter1.section1.config.CustomizeConfigurationByPropertySource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试主体
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicaitonTest1 {
    private final Logger LOGGER = LoggerFactory.getLogger(ApplicaitonTest1.class);

    @Autowired
    protected ApplicationContext context;

    /**
     * 采用@PropertySource注解获取配置文件中的配置
     */
    @Test
    public void testPropertiesSource() {
        LOGGER.info("ahthor:{}", CustomizeConfigurationByPropertySource.author);
    }

    /**
     * 采用@ConfigurationProperty(prefix="${}")注解获取配置文件中国的配置
     */
    @Test
    public void testConfigurationProperty() {
        LOGGER.info("version:{}", CustomizeConfigurationByConfigurationProperty.version);
    }
}
