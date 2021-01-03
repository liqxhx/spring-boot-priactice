package com.lqh.practice.actuator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p> 类描述: MySpringSecurityConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/06 09:38
 * @since 2020/09/06 09:38
 */
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers("/signin.html")
//                .antMatchers("/swagger-ui.html")
//                .antMatchers("/v2/**")
//                .antMatchers("/swagger-resources/**")
//                .antMatchers("/webjars/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/actuator/").permitAll()
                .and()
                .csrf().disable();

    }

}
