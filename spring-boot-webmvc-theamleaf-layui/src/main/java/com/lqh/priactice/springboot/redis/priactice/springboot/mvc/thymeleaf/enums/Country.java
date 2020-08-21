package com.lqh.priactice.springboot.redis.priactice.springboot.mvc.thymeleaf.enums;

import lombok.Getter;

/**
 * <p> 类描述: Nationality
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/20 17:48
 * @since 2020/07/20 17:48
 */
public enum Country {
    CHINA(1, "中国"),
    USA(2, "美国"),
    INDIA(3, "印度"),
    JAPAN(4, "日本");

    @Getter
    private int code;
    @Getter
    private String name;

    Country(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
