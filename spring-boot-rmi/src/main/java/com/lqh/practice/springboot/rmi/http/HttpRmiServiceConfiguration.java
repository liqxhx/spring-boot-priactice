package com.lqh.practice.springboot.rmi.http;

import com.lqh.practice.springboot.rmi.service.UserService;
import com.lqh.practice.springboot.rmi.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

/**
 * <p> 类描述: HttpRmiServiceConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/04 17:16
 * @since 2021/05/04 17:16
 */
@Configuration
public class HttpRmiServiceConfiguration {
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public HessianServiceExporter hessianServiceExporter() {
        HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
        hessianServiceExporter.setService(userService());
        hessianServiceExporter.setServiceInterface(UserService.class);
        return hessianServiceExporter;
    }

    @Bean
    public HttpInvokerServiceExporter httpInvokerServiceExporter() {
        HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
        httpInvokerServiceExporter.setService(userService());
        httpInvokerServiceExporter.setServiceInterface(UserService.class);
        return httpInvokerServiceExporter;
    }
}
