package com.github.cnkeep.web.config.jersey;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 描述: Jersey配置swagger2
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/5/18
 */
@Configuration
public class JserseySwagger2Configuration extends ResourceConfig {

    @Value("${server.servlet.context-path:/}")
    private String contextPath;
    @Autowired
    private ResourceConfig resourceConfig;

    public JserseySwagger2Configuration() {
        packages("com.github.cnkeep.web.resource");
    }

    @PostConstruct
    public void init() {
        this.configureSwagger();
    }

    /**
     * 配置swagger2 </br>
     * 访问地址：http://{ip}:{post}/{context-path}/swagger.json
     */
    private void configureSwagger() {
        // Available at localhost:port/swagger.json
        resourceConfig.register(ApiListingResource.class).
                register(SwaggerSerializers.class);
        BeanConfig config = new BeanConfig();
        config.setConfigId("springboot-jersey-swagger-example");
        config.setTitle("Spring Boot + Jersey + Swagger + Example");
        config.setVersion("v1");
        config.setContact("LeiLi.Zhang");
        config.setSchemes(new String[]{"http"/*, "https"*/});
        config.setBasePath(contextPath);
        config.setResourcePackage("com.github.cnkeep.web.resource");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}
