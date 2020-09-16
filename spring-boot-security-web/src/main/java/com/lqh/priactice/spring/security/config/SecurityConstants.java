package com.lqh.priactice.spring.security.config;

/**
 * <p> 类描述: SecurityConstants
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/08 21:42
 * @since 2020/09/08 21:42
 */
public final class SecurityConstants {
    /**
     * 登录访问url
     * 需要身份验证时要访问的url
     */
    public static final String DEFAULT_LOGIN_URL = "/authentication/require";

    /**
     * html表单提交的验证处理url
     * 当访问这个请求时，就会用{@link org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter}来处理用户登录请求
     */
    public static final String DEFAULT_LOGIN_PROCESSING_FORM_URL = "/authentication/form";

    /**
     * 短信登录
     */
    public static final String DEFAULT_LOGIN_PROCESSING_SMS_URL = "/authentication/sms";
}
