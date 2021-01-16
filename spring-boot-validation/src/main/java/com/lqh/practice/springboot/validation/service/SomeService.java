package com.lqh.practice.springboot.validation.service;

import com.lqh.practice.springboot.validation.domain.ReqDio;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
    @Validated
    public void doSomeThing(@NotBlank(message = "{param.not.blank}") String params, @Valid ReqDio req) {
        System.err.println("do some thing");
    }
}
