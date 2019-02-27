package com.github.cnkeep.web.config.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 描述~
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/16
 */
@Configuration
@ConfigurationProperties(prefix = "datasource.master")
public class MasterDsConfig extends BaseDsConfig {
}
