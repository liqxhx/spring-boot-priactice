package com.lqh.practice.springboot.aop.circuitbreak;

import java.lang.annotation.*;

/**
 * <p> 类描述: CircuitBreaker
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/02/27 10:32
 * @since 2021/02/27 10:32
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CircuitBreaker {

    /**
     * 默认超时时间
     * @return
     */
    int timeout() default 1000;

    /**
     * 回退方法
     * @return
     */
    String fallback() default "";

}
