package com.lqh.priactice.spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p> 类描述: SimpleValidateCode
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/11 09:03
 * @since 2020/09/11 09:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleValidateCode {
    private String code;
    private LocalDateTime expireTime;

    public SimpleValidateCode(String code, int expireAfterSeconds) {
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
        this.code = code;
    }

    public SimpleValidateCode expireAfterSeconds(int seconds) {
        this.expireTime = LocalDateTime.now().plusSeconds(seconds);
        return this;
    }

    public boolean isExpired() {
        return expireTime == null ? Boolean.TRUE.booleanValue() : LocalDateTime.now().isAfter(expireTime);
    }
}
