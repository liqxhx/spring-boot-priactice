package com.lqh.practice.springboot.rmi.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * <p> 类描述: SpringRmiServer
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/04 16:35
 * @since 2021/05/04 16:35
 */
public class SpringRmiServer {
    /**
    * main
    */
    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RmiUserServiceConfiguration.class);

        System.out.println("RmiServiceExporter started");
    }
}
