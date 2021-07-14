package com.lqh.practice.sb.disruptor.demo.msg;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> todo 写点注释吧
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/14 14:23
 * @since 2021/6/14 14:23
 */
@Slf4j
public class RabbitMqMsgSender implements MsgSender{
    @Override
    public void send(Msg msg) {
//        try {
//            TimeUnit.MILLISECONDS.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info("rabbitMQ send msg:{}", msg);
    }
}
