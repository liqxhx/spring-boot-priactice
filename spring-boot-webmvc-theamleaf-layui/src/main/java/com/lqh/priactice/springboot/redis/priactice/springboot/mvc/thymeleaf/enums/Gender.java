package com.lqh.priactice.springboot.redis.priactice.springboot.mvc.thymeleaf.enums;

import lombok.Getter;

/**
 * <p> 类描述: Gender
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/11 19:33
 * @since 2020/07/11 19:33
 */
public enum Gender {
    MALE(1, "男"),
    FAMALE(2, "女");

    @Getter
    private int code;

    @Getter
    private String name;

    Gender(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Gender of(Object val) {
        if(val == null) {
            return null;
        }

        if (val instanceof Gender) {
            return (Gender) val;
        }
        if (val instanceof Number) {
            int id = ((Number) val).intValue();
            for (Gender e : Gender.values()) {
                if (e.code == id) {
                    return e;
                }
            }
        }
        if (val instanceof String) {
            for (Gender e : Gender.values()) {
                if (e.name().equals(val)) {
                    return e;
                }
            }
        }
        return null;
    }
}
