package com.lqh.practice.springboot.validation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Pointcut("execution(public * com.lqh.practice.springboot.validation.service..*.*(..))" +
            " && args(com.lqh.practice.springboot.validation.domain.ReqDio)")
    public void validatePointcut() {
    }

    @Before("validatePointcut()")
    public void before() throws Throwable {
        System.out.println("-------Validated-------");
    }

    private static Validator validator;

    static {
        validator = Validation.byDefaultProvider().configure()
                .messageInterpolator(new ResourceBundleMessageInterpolator(
                        new PlatformResourceBundleLocator("errorMessages")))
                .buildValidatorFactory().getValidator();
    }


    @Before("execution(public * com.lqh.practice.springboot.validation.service..*.*(..)) && @annotation(org.springframework.validation.annotation.Validated)")
    public void before(JoinPoint joinPoint) throws Exception {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Set<ConstraintViolation<Object>> constraintViolations = validator.forExecutables().validateParameters(joinPoint.getThis(), signature.getMethod(), args);
        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<Object> error : constraintViolations) {
            messages.add(error.getMessage());
        }
        if (!messages.isEmpty()) {
            throw new Exception(messages.toString());
        }
    }
}
