package com.lqh.priactice.actuator.dto;

import lombok.*;

import java.io.Serializable;

/**
 * <p> 类描述: ResponseDTO
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/12/16 20:15
 * @since 2020/12/16 20:15
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ResponseDTO<T> implements Serializable {
    private Integer status;
    public String message;
    public T data;

    public static final int STATUS_SUCCESS = 0;

    public ResponseDTO(T data) {
        this.status = STATUS_SUCCESS;
        this.data = data;
    }


    public ResponseDTO(int status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ResponseDTO<T> success(T data) {
        return new ResponseDTO(STATUS_SUCCESS, "success", data);
    }
}
