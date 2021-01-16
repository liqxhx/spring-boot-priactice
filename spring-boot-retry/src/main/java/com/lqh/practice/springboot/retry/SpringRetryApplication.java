package com.lqh.practice.springboot.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * <p> 类描述: SpringRetryApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/16 11:41
 * @since 2021/01/16 11:41
 */
@SpringBootApplication
@EnableRetry // 在主类上加上@EnableRetry注解，表示启用重试机制
public class SpringRetryApplication {
    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run(SpringRetryApplication.class,args);
    }
}
