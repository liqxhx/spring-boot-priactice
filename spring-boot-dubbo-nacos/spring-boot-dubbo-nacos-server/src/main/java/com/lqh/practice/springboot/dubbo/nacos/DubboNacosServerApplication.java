package com.lqh.practice.springboot.dubbo.nacos;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p> 类描述: App
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/03/15 23:39
 * @since 2021/03/15 23:39
 */
@DubboComponentScan(basePackages="com.lqh.practice.springboot.dubbo.nacos")
@SpringBootApplication
public class DubboNacosServerApplication {
    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run(DubboNacosServerApplication.class, args);
    }
}
