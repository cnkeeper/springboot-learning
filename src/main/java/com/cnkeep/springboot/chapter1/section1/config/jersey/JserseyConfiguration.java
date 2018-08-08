package com.cnkeep.springboot.chapter1.section1.config.jersey;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.models.*;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.In;
import io.swagger.models.auth.OAuth2Definition;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 描述~
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/18
 */
@Configuration
public class JserseyConfiguration extends ResourceConfig {

    @Value("${server.context-path:/}")
    private String contextPath;

    @PostConstruct
    public void init() {
        // Register components where DI is needed
        this.configureSwagger();
    }

    public JserseyConfiguration() {
        packages("com.cnkeep.springboot.chapter1.section1");
    }

    private void configureSwagger() {
        // Available at localhost:port/swagger.json
        packages("com.cnkeep.springboot.chapter1.section1.resource").
                register(io.swagger.jaxrs.listing.ApiListingResource.class).
                register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        BeanConfig config = new BeanConfig();
        config.setConfigId("springboot-jersey-swagger-example");
        config.setTitle("Spring Boot + Jersey + Swagger + Example");
        config.setVersion("v1");
        config.setContact("LeiLi.Zhang");
        config.setSchemes(new String[]{"http"/*, "https"*/});
        config.setBasePath(contextPath);
        config.setResourcePackage("com.cnkeep.springboot.chapter1.section1.resource");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}
