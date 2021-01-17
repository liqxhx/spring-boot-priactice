package com.lqh.practice.springboot.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p> 类描述: SpringBootAdminServerApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/17 21:17
 * @since 2021/01/17 21:17
 */
@EnableAdminServer
@SpringBootApplication
public class SpringBootAdminServerApplication {
    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run(SpringBootAdminServerApplication.class, args);
    }
}
