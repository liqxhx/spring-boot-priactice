package com.lqh.practice.common.domain;

import java.io.Serializable;

/**
 * <p> 类描述: Notification
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/03 14:21
 * @since 2021/01/03 14:21
 */
public class Notification implements Serializable {
    public static final int PING = 1;
    public static final int BLACK_WRITE_LIST_REFRESH = 2;

    private Integer type;
    private Object data;

    public Notification(Integer type, Object data) {
        this.type = type;
        this.data = data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "type=" + type +
                ", data=" + data +
                '}';
    }
}
