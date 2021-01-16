package com.lqh.practice.springboot.validation.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p> 类描述: ReqDio
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/15 21:36
 * @since 2021/01/15 21:36
 */
@Data
@Accessors(chain = true)
public class ReqDio {
    @NotNull
    String name;
}
