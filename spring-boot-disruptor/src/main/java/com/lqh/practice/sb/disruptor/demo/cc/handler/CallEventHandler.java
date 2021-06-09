package com.lqh.practice.sb.disruptor.demo.cc.handler;

import com.lqh.practice.sb.disruptor.demo.cc.CallEvent;

/**
 * <p> CallEventHandler
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/8 18:28
 * @since 2021/6/8 18:28
 */
public interface CallEventHandler {
    boolean support(CallEvent event);

    void handle(CallEvent event);
}
