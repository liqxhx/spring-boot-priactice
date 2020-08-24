package com.lqh.priactice.spring.security.exception;

/**
 * <p> 类描述: UserNotExistsException
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/09 08:12
 * @since 2020/07/09 08:12
 */
public class UserNotExistsException extends CommonBizException {
    private Integer userId;
    private String userName;

    public UserNotExistsException(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserNotExistsException() {
        super();
    }

    public UserNotExistsException(String message) {
        super(message);
    }

    public UserNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotExistsException(Throwable cause) {
        super(cause);
    }

    protected UserNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
