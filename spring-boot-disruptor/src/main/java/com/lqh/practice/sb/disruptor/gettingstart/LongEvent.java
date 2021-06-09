package com.lqh.practice.sb.disruptor.gettingstart;

import lombok.ToString;

/**
 * <p> 类描述: 定义传递一个 long 值的事件 LongEvent
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/05 22:20
 * @since 2021/06/05 22:20
 */
@ToString
public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    public long get() {
        return this.value;
    }

    public void clear() {
        Printer.output("\u001b[31m3 " + this + "\u001b[0m");
        this.value = 0; // null
    }
}
