package com.lqh.practice.sb.disruptor.demo.msg;

import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * <p> Disruptor 多消费者 发送Msg
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/14 14:28
 * @since 2021/6/14 14:28
 */
@Slf4j
public class MsgSenderServiceDisruptor {
    Disruptor<Msg> disruptor;
    MsgTranslator msgTranslator;

    public MsgSenderServiceDisruptor() {
        this.disruptor = new Disruptor<>(Msg::new, 1024, DaemonThreadFactory.INSTANCE, ProducerType.MULTI, new YieldingWaitStrategy());

        this.msgTranslator = new MsgTranslator(this.disruptor);

        MsgHandler handler[] = initMsgHandler(10);

        this.disruptor.handleEventsWithWorkerPool(handler);

        this.disruptor.start();

        log.debug("started");
    }

    private MsgHandler[] initMsgHandler(int len) {
        MsgHandler[] h = new MsgHandler[len];

        for (int i = 0; i < len; i++) {
            h[i] = new MsgHandler();
        }

        return h;
    }

    @Test
    public void test() {
        for (int i = 0; ; i++) {
            this.msgTranslator.onMessage(Long.valueOf(i), System.nanoTime(), "test-" + i);
        }
    }
}
