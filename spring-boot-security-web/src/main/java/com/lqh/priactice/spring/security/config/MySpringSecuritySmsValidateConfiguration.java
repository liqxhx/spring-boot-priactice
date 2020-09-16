package com.lqh.priactice.spring.security.config;

import com.lqh.priactice.spring.security.authentication.SmsValidateCodeAuthenticationProvider;
import com.lqh.priactice.spring.security.filter.SmsValidateCodeAuthenticationFilter;
import com.lqh.priactice.spring.security.filter.SmsValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p> 类描述: MySpringSecurityWebConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/12 08:29
 * @since 2020/09/12 08:29
 */
@Configuration
public class MySpringSecuritySmsValidateConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private SmsValidateCodeFilter smsValidateCodeFilter;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsValidateCodeAuthenticationFilter smsValidateCodeAuthenticationFilter = new SmsValidateCodeAuthenticationFilter();
        smsValidateCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsValidateCodeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        smsValidateCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        SmsValidateCodeAuthenticationProvider smsValidateCodeAuthenticationProvider = new SmsValidateCodeAuthenticationProvider();
        smsValidateCodeAuthenticationProvider.setUserDetailsService(userDetailsService);


        http.addFilterBefore(smsValidateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(smsValidateCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(smsValidateCodeAuthenticationProvider)
        ;
    }
}
