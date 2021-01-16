package com.lqh.practice.springboot.validation.aop;

import java.lang.annotation.*;

/**
 * <p> 类描述: NeedValidate
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/15 21:51
 * @since 2021/01/15 21:51
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedValidate {
    Class<?>[] value() default {};
}
