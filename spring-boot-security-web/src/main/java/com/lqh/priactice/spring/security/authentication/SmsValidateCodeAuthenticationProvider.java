package com.lqh.priactice.spring.security.authentication;

import com.lqh.priactice.spring.security.model.SmsValidateCodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p> 类描述: SmsValidateCodeAuthenticationProvider
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/16 21:32
 * @since 2020/09/16 21:32
 */
public class SmsValidateCodeAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsValidateCodeAuthenticationToken token = (SmsValidateCodeAuthenticationToken)authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(String.valueOf(token.getPrincipal()));
        if(userDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        SmsValidateCodeAuthenticationToken smsToken = new SmsValidateCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
        // 将未验证token里的detail设置到已验证的token里
        smsToken.setDetails(token.getDetails());
        return smsToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsValidateCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
