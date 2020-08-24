package com.lqh.priactice.spring.security.controller;

import com.lqh.priactice.spring.security.exception.UserNotExistsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p> 类描述: ControllerExceptionHandler
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/09 08:09
 * @since 2020/07/09 08:09
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistsException.class)
    @ResponseBody
    @ResponseStatus
    public Object handleUserNotExists(UserNotExistsException e) {
        return null;
    }
}
