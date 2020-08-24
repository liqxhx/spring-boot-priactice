package com.lqh.priactice.springboot.mvc.thymeleaf.enums;

import lombok.Getter;

/**
 * <p> 类描述: Hobby
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/20 17:56
 * @since 2020/07/20 17:56
 */
public enum Hobby {
    RUNNING(1, "跑步"),
    SLEEPING(2, "睡觉"),
    FOOD(3, "美食"),
    GAMES(4, "打美食"),
    SWIMMING(5, "游泳"),
    DRINKING(6, "喝酒"),
    POKER(7, "打牌"),
    MOVIES(8, "看电影"),
    FILM(9, "摄影"),
    READING(10, "阅读"),
    WRITING(11, "写作"),
    FOOTBALL(12, "足球"),
    BASKETBALL(13, "蓝球"),
    OTHER(-1, "其它");

    @Getter
    private int code;
    @Getter
    private String name;

    Hobby(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
