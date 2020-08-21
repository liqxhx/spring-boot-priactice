package com.lqh.priactice.springboot.redis.priactice.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p> 类描述: com.lqh.priactice.spring.security.SpringSecurityApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/07 22:55
 * @since 2020/07/07 22:55
 */
// exclude = SecurityAutoConfiguration.class
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories
@EnableSwagger2
@EnableScheduling
@EnableAsync
public class SpringSecurityApplication {
    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run( SpringSecurityApplication.class, args);
    }
}
