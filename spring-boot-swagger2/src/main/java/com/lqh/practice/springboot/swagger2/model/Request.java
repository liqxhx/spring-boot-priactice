package com.lqh.practice.springboot.swagger2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p> 类描述: Request
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/02 21:37
 * @since 2021/04/02 21:37
 */
@Data
@ApiModel(value = "统一Request")
public class Request<T> {
    @ApiModelProperty(value = "签名")
    @NotBlank(message = "签名不能为空")
    private String sign;

    @ApiModelProperty(value = "真实数据")
    private T data;
}
