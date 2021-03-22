package com.lqh.practice.springboot.dubbo;

import com.lqh.practice.common.domain.IPing;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 类描述: nacos
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/03/15 23:50
 * @since 2021/03/15 23:50
 */
@SpringBootApplication
@RestController
public class NacosDubboClientApp {

    
    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run(NacosDubboClientApp.class, args);
    }

    @DubboReference(version = "1.0")
    public IPing ping;

    @GetMapping("/ping")
    public String ping() {
        return ping.ping("hello");
    }
}
