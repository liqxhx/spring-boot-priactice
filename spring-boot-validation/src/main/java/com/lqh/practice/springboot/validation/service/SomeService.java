package com.lqh.practice.springboot.validation.service;

import com.lqh.practice.springboot.validation.aop.NeedValidate;
import com.lqh.practice.springboot.validation.domain.ReqDio;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p> 类描述: SomeService
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/15 21:54
 * @since 2021/01/15 21:54
 */
@Component
public class SomeService {
    public void doSomeThing(@Validated ReqDio req) {

    }
}
