package com.lqh.practice.springboot.rmi.spring;

import com.lqh.practice.springboot.rmi.service.UserService;
import com.lqh.practice.springboot.rmi.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * <p> 类描述: RmiUserServiceConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/04 16:27
 * @since 2021/05/04 16:27
 */
@Configuration
public class RmiUserServiceConfiguration {
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public RmiServiceExporter userServiceRmiServiceExporter() {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("UserService");
        exporter.setService(userService());
        exporter.setServiceInterface(UserService.class);
        exporter.setRegistryPort(1099);
        return exporter;
    }
}
