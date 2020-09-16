package com.lqh.priactice.spring.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p> 类描述: ResponseDTO
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/08 21:50
 * @since 2020/09/08 21:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> implements Serializable {
    public static final int CODE_FAIL = 100;
    public static final int CODE_SUCCESS = 200;
    private Integer code;
    private String message;
    private T data;

    public static ResponseDTO fail(String message) {
       return ResponseDTO.builder().code(CODE_FAIL).message(message).build();
    }

    public static <T> ResponseDTO fail(String message, T data) {
        return ResponseDTO.builder().code(CODE_FAIL).message(message).data(data).build();
    }

    public static ResponseDTO success(String message) {
        return ResponseDTO.builder().code(CODE_SUCCESS).message(message).build();
    }

    public static <T> ResponseDTO success(String message, T data) {
        return ResponseDTO.builder().code(CODE_SUCCESS).message(message).data(data).build();
    }
}
