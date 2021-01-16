package com.lqh.practice.springboot.validation.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
    @NotBlank(message = "{param.not.blank}")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Positive(message = "{param.must.positive}")
    private Integer age;
}
