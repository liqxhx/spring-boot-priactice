package com.lqh.practice.springboot.validation.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <p> 类描述: ValidateAspect
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/15 21:50
 * @since 2021/01/15 21:50
 */
@Aspect
@Component
public class ValidateAspect {
//    @Pointcut("execution(public * com.lqh.practice.springboot.validation.service..*.*(..))" +
//            " && @args(org.springframework.validation.annotation.Validated)")
@Pointcut(" @args(NeedValidate)")
    public void validatePointcut() {
    }

    @Before("validatePointcut()")
    public void before() throws Throwable {
        System.out.println("-------Validated-------");
    }
}
