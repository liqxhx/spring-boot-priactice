package com.lqh.priactice.spring.security.filter;

import com.lqh.priactice.spring.security.config.SecurityConstants;
import com.lqh.priactice.spring.security.exception.CaptchaException;
import com.lqh.priactice.spring.security.model.SmsValidateCode;
import com.lqh.priactice.spring.security.service.CaptchaService;
import com.lqh.priactice.spring.security.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p> 类描述: ImageValidateCodeFilter: 短信验证码校验过滤器
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/10 07:32
 * @since 2020/09/10 07:32
 */
@Component
@Slf4j
public class SmsValidateCodeFilter extends OncePerRequestFilter {
    @Autowired
    private SmsService smsService;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(needValidate(request)) {
           try {
               String mobile = ServletRequestUtils.getStringParameter(request,"mobile");
               String smsValidateCode = ServletRequestUtils.getRequiredStringParameter(request,"smsValidateCode");
               smsService.validate(mobile, smsValidateCode);
           } catch (CaptchaException e) {
               log.warn("验证码校验失败：{}", e.getMessage(), e);
               authenticationFailureHandler.onAuthenticationFailure(request, response, e);
               return;
           }
       }
       filterChain.doFilter(request, response);
    }

    private boolean needValidate(HttpServletRequest request) {
        return SecurityConstants.DEFAULT_LOGIN_PROCESSING_SMS_URL.equalsIgnoreCase(request.getRequestURI()) && "POST".equalsIgnoreCase(request.getMethod());
    }
}
