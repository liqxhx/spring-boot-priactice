package com.lqh.priactice.spring.security.validate;

/**
 * <p> 类描述: ValidationException
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/21 16:53
 * @since 2020/09/21 16:53
 */
public class ValidateException extends RuntimeException {
    public ValidateException() {
        super();
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }

    protected ValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
