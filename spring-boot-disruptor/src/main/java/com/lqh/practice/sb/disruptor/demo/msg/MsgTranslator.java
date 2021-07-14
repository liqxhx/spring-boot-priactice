package com.lqh.practice.sb.disruptor.demo.msg;

import com.lmax.disruptor.EventTranslatorThreeArg;
import com.lmax.disruptor.dsl.Disruptor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> todo 写点注释吧
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/14 0014 15:13
 * @since 2021/6/14 0014 15:13
 */
@Slf4j
public class MsgTranslator {
    private Disruptor<Msg> disruptor;

    private static final EventTranslatorThreeArg<Msg, Long, Long, String> T = new EventTranslatorThreeArg<Msg, Long, Long, String>() {
        @Override
        public void translateTo(Msg event, long sequence, Long id, Long time, String content) {
            event.setMsgId(id);
            event.setMsgSendTime(time);
            event.setContent(content);
        }
    };

    public MsgTranslator(Disruptor<Msg> disruptor) {
        this.disruptor = disruptor;
    }

    public void onMessage(Long id, Long time, String content) {
        this.disruptor.publishEvent(T, id, time, content);
        log.info("enRingBuffer {} {}", id, this.disruptor.getRingBuffer().remainingCapacity());
    }
}
