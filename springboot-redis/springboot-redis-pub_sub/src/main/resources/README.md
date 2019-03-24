spring-redis-pub_sub
-------------------

## 1. PUB/SUB Message 组件
### 1.1 介绍 
&nbsp;&nbsp;基于``spring-boot-starter-data-redis``的发布/订阅消息实现，目的是简化操作流程。

### 1.2 快速开始
> 下面将介绍如何快速使用该功能。
#### 1. 新建一个Springboot Project
[https://start.spring.io/](https://start.spring.io/)

#### 2. 引入依赖
```text
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
            <version>2.1.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
            <version>2.1.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
            <version>2.6.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
            <version>2.1.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>com.github.cnkeep</groupId>
            <artifactId>springboot-redis-pub_sub</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```

#### 2. 构建自己的SubscribeMessageListener(消息监听器)
```java
/**
* Attention!!! The @Component annotation is required which will can be scanned and injected by spring container.
*/
@Component
public class BusinessSubscribeMessageListener implements SubscribeMessageListener {
    /**
     * Logger
     * */
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessSubscribeMessageListener.class);

    @Override
    public Topic topic() {
        return new PatternTopic("Topic");
    }

    @Override
    public void receiveMessage(Object message, String channel) {
        LOGGER.info("get message[{}], from channel[{}]",message,channel);
    }
}
```

#### 3. 在*application.yml*中配置Redis
```text
spring:
  application:
    name: springboot-redis-sample
  redis:
    # 其他配置信息有缺省
    host: 172.16.22.135
    port: 6379
    password: <password>
    timeout: 500
    lettuce:
      pool:
        min-idle: 1
        max-idle: 8
        max-active: 8
```

#### 4. 在启动类上使用@EnableRedisPubAndSub注解开启服务
```text
@EnableRedisPubAndSub
@SpringBootApplication
public class SpringBootRedisApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisApplicationTest.class, args);
    }
}
```
