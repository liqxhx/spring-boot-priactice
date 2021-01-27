package com.lqh.practice.spi.driver.demo;

import com.lqh.practice.spi.driver.SoftSwitchOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ServiceLoader;

/**
 * <p> 类描述: SpiApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/27 21:50
 * @since 2021/01/27 21:50
 */
@SpringBootApplication
public class SpiApplication {
    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run(SpiApplication.class, args);
        ServiceLoader<SoftSwitchOperations> options = ServiceLoader.load(SoftSwitchOperations.class);
        for (SoftSwitchOperations option : options) {
            System.out.println(option.ping("hello world"));
        }
    }
}
