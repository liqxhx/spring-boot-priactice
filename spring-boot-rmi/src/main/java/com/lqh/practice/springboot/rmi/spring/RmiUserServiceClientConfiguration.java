package com.lqh.practice.springboot.rmi.spring;

import com.lqh.practice.springboot.rmi.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * <p> 类描述: RmiUserServiceConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/04 16:27
 * @since 2021/05/04 16:27
 */
@Configuration
public class RmiUserServiceClientConfiguration {
/*    @Bean
    public UserService userService() {
        RmiProxyFactoryBean factory = new RmiProxyFactoryBean();
        factory.setServiceInterface(UserService.class);
        factory.setServiceUrl("rmi://localhost:1099/UserService");
        factory.afterPropertiesSet();
        return (UserService) factory.getObject();
    }*/

    @Bean
    public RmiProxyFactoryBean rmiProxyFactoryBean() {
        RmiProxyFactoryBean factory = new RmiProxyFactoryBean();
        factory.setServiceInterface(UserService.class);
        factory.setServiceUrl("rmi://localhost:1099/UserService");
        return factory;
    }
}
