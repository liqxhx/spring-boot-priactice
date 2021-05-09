package com.lqh.practice.springboot.rmi.spring;

import com.lqh.practice.common.domain.User;
import com.lqh.practice.springboot.rmi.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * <p> 类描述: SpringRmiClient
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/04 16:41
 * @since 2021/05/04 16:41
 */
public class SpringRmiClient {
    /**
    * main
    */
    public static void main(String[] args){
//        RmiProxyFactoryBean factory = new RmiProxyFactoryBean();
//        factory.setServiceInterface(UserService.class);
//        factory.setServiceUrl("rmi://localhost:1099/UserService");
//        factory.afterPropertiesSet();
//
//        UserService userService = (UserService)factory.getObject();
//
//        User user = userService.create("lqh", "123456");
//        System.out.println(user);


        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RmiUserServiceClientConfiguration.class);
        UserService userService = ctx.getBean(UserService.class);

        System.out.println(userService.create("liqh", "123456"));

    }
}
