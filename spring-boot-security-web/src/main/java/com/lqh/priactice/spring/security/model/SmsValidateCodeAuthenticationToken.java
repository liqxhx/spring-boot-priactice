package com.lqh.priactice.spring.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * <p> 类描述: SmsValidateCodeToken
 * <p>参考：{@link org.springframework.security.authentication.UsernamePasswordAuthenticationToken}
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/12 08:54
 * @since 2020/09/12 08:54
 */
public class SmsValidateCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    // ~ Instance fields
    // ================================================================================================

    /**
     * 认证前为提交的手机号，认证后为用户的信息:UserDetails
     */
    private final Object principal;
//    private Object credentials;

    /**
     * 认证前为提交的手机号
     * @param mobile
     */
    public SmsValidateCodeAuthenticationToken(Object mobile) {
        super(null);
        this.principal = mobile;
        setAuthenticated(false);
    }

    /**
     * 认证后为用户的信息:UserDetails
     * @param principal
     * @param authorities
     */
    public SmsValidateCodeAuthenticationToken(Object principal,
                                               Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    // ~ Methods
    // ========================================================================================================
    @Override
    public Object getCredentials() {
//        return this.credentials;
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
//        credentials = null;
    }
}
