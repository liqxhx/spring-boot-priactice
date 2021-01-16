package com.lqh.practice.springboot.validation;

import com.lqh.practice.springboot.validation.domain.ReqDio;
import com.lqh.practice.springboot.validation.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.validation.annotation.Validated;

/**
 * <p> 类描述: ServiceValidationApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/15 22:22
 * @since 2021/01/15 22:22
 */
@SpringBootApplication
public class ServiceValidationApplication implements CommandLineRunner {
    @Autowired
    SomeService someService;

    /**
    * main
    */
    public static void main(String[] args){
        new SpringApplicationBuilder().sources(ServiceValidationApplication.class).web(WebApplicationType.NONE).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        ReqDio req = new ReqDio();
        req.setName("test");
        someService.doSomeThing(req);
    }
}
