package com.lqh.practice.sb.disruptor.demo.msg;

/**
 * <p> todo 写点注释吧
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/14 0014 14:17
 * @since 2021/6/14 0014 14:17
 */
public interface MsgSender {
    void send(Msg msg);
}
