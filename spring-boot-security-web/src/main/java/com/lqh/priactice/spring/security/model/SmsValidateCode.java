package com.lqh.priactice.spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p> 类描述: SmsValidateCode
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/11 09:06
 * @since 2020/09/11 09:06
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class SmsValidateCode extends SimpleValidateCode {
    private String mobile;

    public SmsValidateCode(String code, String mobile, int expireAfterSeconds) {
        super(code, expireAfterSeconds);
        this.mobile = mobile;
    }
}
