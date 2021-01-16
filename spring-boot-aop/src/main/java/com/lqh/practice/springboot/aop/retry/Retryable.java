package com.lqh.practice.springboot.aop.retry;

import java.lang.annotation.*;

/**
 * <p> 类描述: Retryable
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/16 11:16
 * @since 2021/01/16 11:16
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Retryable {

    /**
     * Exception type that are retryable.
     * @return exception type to retry
     */
    Class<? extends Throwable> value() default RuntimeException.class;

    /**
     * 包含第一次失败
     * @return the maximum number of attempts (including the first failure), defaults to 3
     */
    int maxAttempts() default 3;

}
