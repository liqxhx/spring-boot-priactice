package com.lqh.priactice.spring.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * <p> 类描述: CaptchaException
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/09 21:50
 * @since 2020/09/09 21:50
 */
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public CaptchaException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }
}
