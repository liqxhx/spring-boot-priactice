package com.lqh.practice.springboot.disruptor.uid;

/**
 * <p> 类描述: IdSegment
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/15 20:46
 * @since 2021/04/15 20:46
 */
public class ID {
    private long value;

    public ID(){}

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
