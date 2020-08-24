package com.lqh.priactice.springboot.springsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p> 类描述: TODO
 *
 * @author qhlee
 * @version TODO
 * @date 2020/06/15 11:36
 * @since 2020/06/15 11:36
 *
 * https://www.jianshu.com/p/106f0eae07c8
 */
@SpringBootApplication
//@EnableRedisHttpSession
public class SessionApplication {
    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run(SessionApplication.class, args);
    }

}
