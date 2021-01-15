package com.lqh.practice.common.domain;

import lombok.Getter;

/**
 * <p> 类描述: Gender
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/15 21:13
 * @since 2021/01/15 21:13
 */
public enum Gender {
    MALE(1, "男"),
    FAMALE(2, "女");

    Gender(int code, String label) {
        this.code = code;
        this.label = label;
    }
    @Getter
    int code;
    @Getter
    String label;

    public static Gender of(int code) {
        for(Gender g : Gender.values()) {
            if(code == g.code) { return g;}
        }
        return null;
    }
}
