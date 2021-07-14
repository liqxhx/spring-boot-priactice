package com.lqh.practice.sb.disruptor.demo.cc;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.lqh.practice.sb.disruptor.demo.cc.handler.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * <p> todo 写点注释吧
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/9 0009 14:45
 * @since 2021/6/9 0009 14:45
 */
@Slf4j
public class CallEventFlowProcessor {
    private final Disruptor<CallEvent> disruptor;
    private final CallSessionManager callSessionManager = new CallSessionManager();

    public CallEventFlowProcessor() {
        disruptor = new Disruptor<>(CallEvent::new, 1024, DaemonThreadFactory.INSTANCE, ProducerType.MULTI, new BlockingWaitStrategy());

        setupHandlers();

        disruptor.start();
    }

    private void setupHandlers() {
        ArrivedHandler1[] arrivedHandlers = initArrivedHandler(20);
        AcceptHandler2[] acceptHandlers = initAcceptHandler(10);
        WelcomeHandler3[] welcomeHandlers = initWelcomeHandler(10);
        IVRHandler4[] ivrHandlers = initIVRHandler(20);
        DispatchHandler5[] dispatchHandlers = initDispatchHandler(50);
        RingHandler6[] ringHandlers = initRingHandler(5);
        AnswerHandler7[] answerHandlers = initAnswerHandler(5);
        AnsweredHandler8[] answeredHandlers = initAnsweredHandler(5);
        ScoreHandler9[] scoreHandlers = initScoreHandler(10);
        ByeHandler10[] byeHandlers = initByeHandler(5);

        disruptor.setDefaultExceptionHandler(new CallEventExceptionHandler());

        disruptor.handleEventsWithWorkerPool(arrivedHandlers)
                .handleEventsWithWorkerPool(acceptHandlers)
                .handleEventsWithWorkerPool(welcomeHandlers)
                .handleEventsWithWorkerPool(ivrHandlers)
                .handleEventsWithWorkerPool(dispatchHandlers)
                .handleEventsWithWorkerPool(ringHandlers)
                .handleEventsWithWorkerPool(answerHandlers)
                .handleEventsWithWorkerPool(answeredHandlers)
                .handleEventsWithWorkerPool(scoreHandlers)
                .handleEventsWithWorkerPool(byeHandlers);
    }

    private ScoreHandler9[] initScoreHandler(int len) {
        ScoreHandler9[] handlers = new ScoreHandler9[len];
        for (int i = 0; i < len; i++) {
            handlers[i] = new ScoreHandler9(this.callSessionManager);
        }
        return handlers;
    }

    private ByeHandler10[] initByeHandler(int len) {
        ByeHandler10[] handlers = new ByeHandler10[len];
        for (int i = 0; i < len; i++) {
            handlers[i] = new ByeHandler10(this.callSessionManager);
        }
        return handlers;
    }

    private AnsweredHandler8[] initAnsweredHandler(int len) {
        AnsweredHandler8[] handlers = new AnsweredHandler8[len];
        for (int i = 0; i < len; i++) {
            handlers[i] = new AnsweredHandler8(this.callSessionManager);
        }
        return handlers;
    }

    private AnswerHandler7[] initAnswerHandler(int len) {
        AnswerHandler7[] handlers = new AnswerHandler7[len];
        for (int i = 0; i < len; i++) {
            handlers[i] = new AnswerHandler7(this.callSessionManager);
        }
        return handlers;
    }

    private RingHandler6[] initRingHandler(int len) {
        RingHandler6[] handlers = new RingHandler6[len];
        for (int i = 0; i < len; i++) {
            handlers[i] = new RingHandler6(this.callSessionManager);
        }
        return handlers;
    }

    private DispatchHandler5[] initDispatchHandler(int len) {
        DispatchHandler5[] handlers = new DispatchHandler5[len];
        for (int i = 0; i < len; i++) {
            handlers[i] = new DispatchHandler5(this.callSessionManager);
        }
        return handlers;
    }

    private IVRHandler4[] initIVRHandler(int len) {
        IVRHandler4[] handlers = new IVRHandler4[len];
        for (int i = 0; i < len; i++) {
            handlers[i] = new IVRHandler4(this.callSessionManager);
        }
        return handlers;
    }

    private WelcomeHandler3[] initWelcomeHandler(int len) {
        WelcomeHandler3[] handlers = new WelcomeHandler3[len];
        for (int i = 0; i < len; i++) {
            handlers[i] = new WelcomeHandler3(this.callSessionManager);
        }
        return handlers;
    }

    private AcceptHandler2[] initAcceptHandler(int len) {
        AcceptHandler2[] handlers = new AcceptHandler2[len];
        for (int i = 0; i < len; i++) {
            handlers[i] = new AcceptHandler2(this.callSessionManager);
        }
        return handlers;
    }

    private ArrivedHandler1[] initArrivedHandler(int len) {
        ArrivedHandler1[] arrivedHandlers = new ArrivedHandler1[len];
        for (int i = 0; i < len; i++) {
            arrivedHandlers[i] = new ArrivedHandler1(this.callSessionManager);
        }
        return arrivedHandlers;
    }

    public void process(CallEvent event) {
        Optional.ofNullable(this.disruptor).ifPresent(d -> {
            d.publishEvent((e, seq, val) -> {
                e.setEventTime(val.getEventTime());
                e.setEventType(val.getEventType());
                e.setIp(val.getIp());
                e.setSessionId(val.getSessionId());
                e.setCallee(val.getCallee());
                e.setCaller(val.getCaller());
            }, event);
            log.debug("produce " + event);
        });

    }

    public void shutdown() {
        Optional.ofNullable(this.disruptor).ifPresent(d -> {
            d.shutdown();
            log.warn("shutdown");
        });
    }
}
