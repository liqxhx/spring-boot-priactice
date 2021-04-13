package com.lqh.practice.springboot.shiro.config;

import com.lqh.practice.springboot.shiro.realm.CustomRealm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> 类描述: ShiroConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/10 09:27
 * @since 2021/04/10 09:27
 */
@Configuration
public class ShiroConfiguration {
    @Bean
    CustomRealm myRealm() {
        return new CustomRealm();
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }

//    @Bean
//    ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
//        definition.addPathDefinition("/doLogin", "anon");
//        definition.addPathDefinition("/**", "authc");
//        return definition;
//    }


}
