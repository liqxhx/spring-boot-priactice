package com.lqh.priactice.springboot.redis.priactice.springboot.mvc.thymeleaf.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> 类描述: ResponseDTO
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/10 17:00
 * @since 2020/07/10 17:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO<T> {

    public static final int CODE_SUCCESS = 0;
    public static final int CODE_FAIL = 100;

    private int code;
    private Integer count;
    private String message;
    private T data;

    public static <T> ResponseDTO success(T data, Integer count){
        return ResponseDTO.builder().code(CODE_SUCCESS).count(count).data(data).build();
    }

    public static <T> ResponseDTO success(T data) {
        return success(data, null);
    }

    public static <T> ResponseDTO fail(T data) {
        return ResponseDTO.builder().code(CODE_FAIL).data(data).build();
    }

    public boolean isSuccess() {
        return this.code == CODE_SUCCESS;
    }
}
