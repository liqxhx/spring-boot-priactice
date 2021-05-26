package com.lqh.practice.springboot.cache.simple;

import com.lqh.practice.springboot.cache.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.support.SimpleCacheManager;

/**
 * <p> 类描述: SimpleCacheManagerRunner
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/26 22:24
 * @since 2021/05/26 22:24
 */
@SpringBootApplication
public class SimpleCacheManagerRunner implements ApplicationRunner {
    @Autowired
    UserService userService;

    @Autowired
    UserService2 userService2;

    @Autowired
    SimpleCacheManager simpleCacheManager;
    /**
    * main
    */
    public static void main(String[] args){
        new SpringApplicationBuilder(SimpleCacheManagerRunner.class).web(WebApplicationType.NONE).run(args);
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

//        User u1 = new User(1L, "lqh");
//        userService.saveOrUpdate(u1);
//        System.out.println(userService.getUser(u1));
//        userService.remove(u1);
//        System.out.println(userService.getUser(u1));
//        System.out.println("==============");

        User u2 = new User(2L, "lqh");
                System.out.println(userService2.getUser(u2));
        System.out.println(userService2.getUser(u2));
        System.out.println("==============");

    }
}
