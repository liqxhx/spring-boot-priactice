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

    int STATE_IVR = 4;

    int STATE_DISPATCH = 5;

    int STATE_AGENT_RING = 6;

    int STATE_AGENT_ANSWER = 7;

    int STATE_ANSWERED = 8;

    int STATE_SCORE = 9;

    int STATE_BYE = 10;

    int getCallState();
}
