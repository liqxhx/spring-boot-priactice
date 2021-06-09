package com.lqh.practice.sb.disruptor.demo.cc.handler;

/**
 * <p> todo 写点注释吧
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/9 0009 14:38
 * @since 2021/6/9 0009 14:38
 */
public interface StateNode {
    int STATE_ARRIVED = 1;

    int STATE_ACCEPTED = 2;

    int STATE_WELCOME = 3;

    int STATE_INCOMING = 4;

    int STATE_IVR = 5;

    int STATE_DISPATCH_STAT = 6;

    int STATE_DIS$PATCH_END = 7;

    int STATE_AGENT_RING = 8;

    int STATE_AGENT_ANSWER = 9;

    int STATE_TALKING = 10;

    int STATE_EVALUATE = 11;

    int STATE_HUNG_UP = 12;

    int STATE_RECORD = 13;

    public abstract int getCallState();
}
