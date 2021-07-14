package com.lqh.practice.sb.disruptor.demo.msg;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * <p> todo 写点注释吧
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/14 14:29
 * @since 2021/6/14 14:29
 */
@Slf4j
public class MsgHandler implements WorkHandler<Msg>, EventHandler<Msg> {
    @Override
    public void onEvent(Msg event, long sequence, boolean endOfBatch) throws Exception {
        onMsg(event);
    }

    @Override
    public void onEvent(Msg event) throws Exception {
        onMsg(event);
    }

    public void onMsg(Msg event) {
        if(new Random(System.nanoTime()).nextBoolean()) {
            MsgSendStrategy.MQ_RABBIT.getSender().send(event);
        } else {
            MsgSendStrategy.HTTP.getSender().send(event);
        }
    }
}
