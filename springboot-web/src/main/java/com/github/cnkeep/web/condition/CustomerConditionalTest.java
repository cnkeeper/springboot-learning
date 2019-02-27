package com.github.cnkeep.web.condition;

import com.github.cnkeep.web.domain.entity.BaseEntity;
import com.github.cnkeep.web.domain.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 描述: @Conditional+ Condition自动配置的核心
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/8/3
 */
@Configuration
@Conditional(CustomerConditonal.class)
public class CustomerConditionalTest {
    @Bean
    public BaseEntity getStudent(){
        return new User();
    }
}
