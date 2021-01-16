package com.lqh.practice.springboot.aop;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * <p> 类描述: AopApplicatioin
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/16 11:29
 * @since 2021/01/16 11:29
 */
@SpringBootApplication
public class AopApplicatioin {
    /**
    * main
    */
    public static void main(String[] args){
        new SpringApplicationBuilder().sources(AopApplicatioin.class).web(WebApplicationType.NONE).run(args);
    }
}
