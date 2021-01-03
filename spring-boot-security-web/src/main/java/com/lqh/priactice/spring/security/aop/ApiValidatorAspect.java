package com.lqh.priactice.spring.security.aop;

import com.lqh.priactice.spring.security.dto.ResponseDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * <p> 类描述: ApiValidatorAspect
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/21 16:56
 * @since 2020/09/21 16:56
 */

@Aspect
//@Component
public class ApiValidatorAspect {
    @Around("execution(* com.lqh.priactice.spring.security.controller.*.*(..)) && args(.., bindingResult)")
    public Object doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
        Object result;
        if (bindingResult.hasErrors()) {
            result = fail(bindingResult);
        } else {
            result = pjp.proceed();
        }
        return result;
    }
    private Object fail(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        return ResponseDTO.fail("请求参数校验失败", errors);
//        errorInfo.setErrorMessage(errors.get(0).getDefaultMessage());
//        return result;
    }

}
