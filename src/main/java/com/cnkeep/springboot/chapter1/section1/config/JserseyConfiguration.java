package com.cnkeep.springboot.chapter1.section1.config;

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
        Info info = new Info()
                .title("Swagger Petstore")
                .description("This is a sample server Petstore server.  You can find out more about Swagger " +
                        "at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, " +
                        "you can use the api key `special-key` to test the authorization filters.")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact()
                        .email("apiteam@swagger.io"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));
        Swagger swagger = new Swagger().info(info);
        swagger.externalDocs(new ExternalDocs("Find out more about Swagger", "http://swagger.io"));
        swagger.securityDefinition("api_key", new ApiKeyAuthDefinition("api_key", In.HEADER));
        swagger.securityDefinition("petstore_auth",
                new OAuth2Definition()
                        .implicit("http://petstore.swagger.io/api/oauth/dialog")
                        .scope("read:pets", "read your pets")
                        .scope("write:pets", "modify pets in your account"));
        swagger.tag(new Tag()
                .name("user")
                .description("Operations about user")
                .externalDocs(new ExternalDocs("Find out more about our store", "http://swagger.io")));
        config.configure(swagger);
    }
}
