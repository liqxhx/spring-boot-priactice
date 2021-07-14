package com.lqh.practice.sb.disruptor.demo.msg;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> todo 写点注释吧
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/14 0014 14:22
 * @since 2021/6/14 0014 14:22
 */
@Slf4j
public class HttpMsgSenderSender implements MsgSender {
    @Override
    public void send(Msg msg) {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info("http send msg:{}", msg);
    }
}
