package com.lqh.priactice.springboot.redis.priactice.spring.security.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p> 类描述: ValueIn
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/08 22:35
 * @since 2020/07/08 22:35
 */
@Documented
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ValueInValidator.class)
public @interface ValueIn {
    String message() default "{javax.validation.constraints.ValueIn.message}";

    Class<?>[] groups() default { };

    String[] value() default {};

    boolean caseSensitive() default false;

    boolean nullable() default true;

    Class<? extends Payload>[] payload() default { };
}
