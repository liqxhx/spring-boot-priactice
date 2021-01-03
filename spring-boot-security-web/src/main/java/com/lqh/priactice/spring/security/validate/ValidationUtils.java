package com.lqh.priactice.spring.security.validate;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collection;
import java.util.Set;

/**
 * <p> 类描述: ValidationUtils
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/21 16:53
 * @since 2020/09/21 16:53
 */
@Slf4j
public class ValidationUtils {
    public static <T> void validate(Collection<T> collection){
        for(T t : collection) {
            validate(t);
        }
    }

    public static <T> void validate(T t){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if(!constraintViolations.isEmpty()) {
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                log.error("#xno#validation#{}#{}", constraintViolation.getMessage(), t);
                throw new ValidateException(constraintViolation.getMessage());
            }
        }
    }
}
