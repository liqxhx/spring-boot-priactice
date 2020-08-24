package com.lqh.priactice.spring.security.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * <p> 类描述: ValueInValidator
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/08 22:46
 * @since 2020/07/08 22:46
 */
public class ValueInValidator implements ConstraintValidator<ValueIn, Object> {
    private String[] values;
    private boolean caseSensitive;
    private boolean nullable;

    @Override
    public void initialize(ValueIn constraintAnnotation) {
        values = constraintAnnotation.value();
        nullable = constraintAnnotation.nullable();
        caseSensitive = constraintAnnotation.caseSensitive();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(nullable == true && value == null) {
            return true;
        }

        if(value == null) {
            return false;
        }

        if(values == null || values.length == 0) {
            return false;
        }

        return Arrays.stream(values).anyMatch(v -> {if(caseSensitive) {return v.equalsIgnoreCase(value.toString());} else {return v.equals(value.toString());}});
    }
}
