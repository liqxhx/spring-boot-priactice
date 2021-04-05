package com.lqh.practice.springboot.swagger2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> 类描述: Response
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/02 21:37
 * @since 2021/04/02 21:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public static Response success() {
       return Response.builder().code(200).message("成功").build();
    }
}
