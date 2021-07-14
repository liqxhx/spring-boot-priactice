package com.lqh.practice.sb.disruptor.demo.cc;

import lombok.Getter;

/**
 * <p> todo 写点注释吧
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/9 0009 15:56
 * @since 2021/6/9 0009 15:56
 */
@Getter
public enum CallEventType {
    INVITE(1, "来电请求"),
    INCOMING(2, "来电"),
    RING(3, "座席响铃"),
    ANSWER(4, "ANSWER"),
    ANSWERED(5, "ANSWERED"),
    SCORE(6, "用户评价"),
    BYE(7, "挂断");

    private int code;
    private String desc;

    CallEventType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
