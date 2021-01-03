package com.lqh.priactice.spring.security.config;

import com.lqh.priactice.spring.security.filter.ImageValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * <p> 类描述: MySpringSecurityConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/06 09:38
 * @since 2020/09/06 09:38
 */
@Configuration
public class MySpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private ImageValidateCodeFilter imageValidateCodeFilter;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MySpringSecuritySmsValidateConfiguration mySpringSecurityWebConfiguration;

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
        // 表单验证
        http.addFilterBefore(imageValidateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            // 表单验证
            .formLogin()
            .loginPage(SecurityConstants.DEFAULT_LOGIN_URL)
            .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_FORM_URL)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
            // remeber me
//            .and().rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(3600)
            //
            .and()
            // 声明下面要进行请求授权配置
            .authorizeRequests()
            // swagger
            .antMatchers("/swagger-ui.html", "/v2/**", "/swagger-resources/**", "/webjars/**").permitAll()
            // login
            .antMatchers(SecurityConstants.DEFAULT_LOGIN_URL, "/validate/code/**", "/signin.html",  SecurityConstants.DEFAULT_LOGIN_PROCESSING_SMS_URL).permitAll()
            .antMatchers("/ping*", "/user/**").permitAll()
                // 所有请求
            .anyRequest()
            // 都需要认证
            .authenticated()
        .and()
                // 跨站请求防护
        .csrf().disable()
        .apply(mySpringSecurityWebConfiguration);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl ret = new JdbcTokenRepositoryImpl();
        // create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, "
        //			+ "token varchar(64) not null, last_used timestamp not null)
        ret.setCreateTableOnStartup(true);
        ret.setDataSource(dataSource);
        return ret;
    }
}
