package com.github.cnkeep.web.config.josn;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 描述: 配置json序列化和反序列化支持LocalDate, LocalDateTime
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/2/28
 */
@Configuration
public class JsonConfiguration {
    @Value("${spring.jackson.date-format}")
    private String pattern;

    @Bean
    public LocalDateSerializer localDateDeserializer() {
        return new LocalDateSerializer(DateTimeFormatter.ofPattern(pattern));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.serializerByType(LocalDate.class, localDateDeserializer());
    }

}
