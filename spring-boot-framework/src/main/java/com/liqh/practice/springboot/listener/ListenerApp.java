package com.liqh.practice.springboot.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p> 类描述: InitializerApp
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/20 22:34
 * @since 2021/05/20 22:34
 */
@SpringBootApplication
public class ListenerApp {
    /**
     * main
     */
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ListenerApp.class);
        springApplication.addListeners(new SecondListener());
        springApplication.run(args);
    }
}
