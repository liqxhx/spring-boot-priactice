package com.lqh.practice.sb.disruptor.demo.cc;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.lqh.practice.sb.disruptor.demo.cc.handler.AcceptHandler;
import com.lqh.practice.sb.disruptor.demo.cc.handler.ArrivedHandler;
import com.lqh.practice.sb.disruptor.demo.cc.handler.CallEventExceptionHandler;
import com.lqh.practice.sb.disruptor.demo.cc.handler.WelcomeHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> todo 写点注释吧
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/9 0009 14:45
 * @since 2021/6/9 0009 14:45
 */
@Slf4j
public class CallEventProcessor {
    private final Disruptor<CallEvent> disruptor;
    CallSessionManager callSessionManager = new CallSessionManager();

    public CallEventProcessor() {
        disruptor = new Disruptor<>(CallEvent::new, 1024, DaemonThreadFactory.INSTANCE, ProducerType.MULTI, new BlockingWaitStrategy());

        ArrivedHandler[] arrivedHandlers = initArrivedHandler(20);
        AcceptHandler[] acceptHandlers = initAcceptHandler(10);
        WelcomeHandler[] welcomeHandlers = initWelcomeHandler(10);


        disruptor.setDefaultExceptionHandler(new CallEventExceptionHandler());

        disruptor.handleEventsWithWorkerPool(arrivedHandlers).handleEventsWithWorkerPool(acceptHandlers).handleEventsWithWorkerPool(welcomeHandlers);

//        EventHandlerGroup<CallEvent> g1 = disruptor.handleEventsWithWorkerPool(arrivedHandlers);
//        g1.thenHandleEventsWithWorkerPool(acceptHandlers);
//        g1.thenHandleEventsWithWorkerPool(welcomeHandlers);

//        g1.and(g2);

        disruptor.start();
    }

    private WelcomeHandler[] initWelcomeHandler(int len) {
        WelcomeHandler[] handlers = new WelcomeHandler[len];
        for (int i = 0; i < len; i++) {
            handlers[i] = new WelcomeHandler(this.callSessionManager);
        }
        return handlers;
    }

    private AcceptHandler[] initAcceptHandler(int len) {
        AcceptHandler[] handlers = new AcceptHandler[len];
        for (int i = 0; i < len; i++) {
            handlers[i] = new AcceptHandler(this.callSessionManager);
        }
        return handlers;
    }

    private ArrivedHandler[] initArrivedHandler(int len) {
        ArrivedHandler[] arrivedHandlers = new ArrivedHandler[len];
        for (int i = 0; i < len; i++) {
            arrivedHandlers[i] = new ArrivedHandler(this.callSessionManager);
        }
        return arrivedHandlers;
    }

    public void process(CallEvent event) {
        disruptor.publishEvent((e, seq, val) -> {
                e.setEventId(val.getEventId());
                e.setEventTime(val.getEventTime());
                e.setEventType(val.getEventType());
                e.setIp(val.getIp());
                e.setSessionId(val.getSessionId());
            }, event);
        log.debug("produce " + event);
    }
}
