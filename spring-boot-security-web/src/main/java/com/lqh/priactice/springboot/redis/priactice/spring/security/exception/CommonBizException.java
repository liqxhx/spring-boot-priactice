package com.lqh.priactice.springboot.redis.priactice.spring.security.exception;

/**
 * <p> 类描述: CommonException
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/09 07:49
 * @since 2020/07/09 07:49
 */
public class CommonBizException extends RuntimeException{
    public CommonBizException() {
        super();
    }

    public CommonBizException(String message) {
        super(message);
    }

    public CommonBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonBizException(Throwable cause) {
        super(cause);
    }

    protected CommonBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
