package com.lqh.priactice.spring.security.controller;

import com.lqh.priactice.spring.security.dto.ResponseDTO;
import com.lqh.priactice.spring.security.exception.UserNotExistsException;
import com.lqh.priactice.spring.security.validate.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> 类描述: ControllerExceptionHandler
 * @author qhlee
 * @version 1.0
 * @date 2020/07/09 08:09
 * @since 2020/07/09 08:09
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleUserNotExists(UserNotExistsException e) {
        log.info("#exception#handle#UserNotExistsException:{}", e);
        return null;
    }

    @ExceptionHandler(ValidateException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleValidateException(ValidateException e) {
        log.info("#exception#handle#ValidateException:{}", e);
        return ResponseDTO.fail(e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        log.info("#exception#handle#ConstraintViolationException:{}", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * https://juejin.im/post/6844903991621451789
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidExceptions(
            MethodArgumentNotValidException ex) {
        log.info("#exception#handle#MethodArgumentNotValidException:{}", ex);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


}
