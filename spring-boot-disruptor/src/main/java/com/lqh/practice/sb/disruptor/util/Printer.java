package com.lqh.practice.sb.disruptor.util;

/**
 * <p> 类描述: Printer
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/05 16:30
 * @since 2021/06/05 16:30
 */
public final class Printer {
    public static void output(String content) {
        System.out.println(Thread.currentThread().getName() + ":" + content);
    }
}
