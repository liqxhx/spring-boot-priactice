package com.lqh.practice.sb.disruptor.gettingstart;

import lombok.Getter;
import lombok.Setter;
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

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private String h1;

    @Getter
    @Setter
    private String h2;

    @Getter
    @Setter
    private String h3;

    @Getter
    @Setter
    private String h4;

    public void set(long value) {
        this.value = value;
    }

    public long get() {
        return this.value;
    }

    public void clear() {
//        Printer.output("\u001b[31m3 " + this + "\u001b[0m");
        this.value = 0; // null
        this.content = null;
        this.h1 = null;
        this.h2 = null;
        this.h3 = null;
        this.h4 = null;
    }

    public LongEvent() {}

    public LongEvent(long value) {
        this.value = value;
    }
}
