package com.lqh.practice.sb.disruptor.demo.cc;

import java.io.IOException;

/**
 * <p> CallDemoApplication
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/8 18:52
 * @since 2021/6/8 18:52
 */

public class CallDemoApplication {
    public static void main(String[] args) throws IOException {
        CallEventFlowProcessor processor = new CallEventFlowProcessor();

        CallEvent invite = new CallEvent(CallEventType.INVITE, "1", System.currentTimeMillis(), "localhost", "2997", "2990");
        processor.process(invite);

        CallEvent incoming = invite;
        incoming.setEventType(CallEventType.INCOMING);
        incoming.setEventTime(System.currentTimeMillis());
        processor.process(incoming);

        CallEvent ring = incoming;
        ring.setEventType(CallEventType.RING);
        ring.setEventTime(System.currentTimeMillis());
        processor.process(ring);

        CallEvent answer = ring;
        answer.setEventType(CallEventType.ANSWER);
        answer.setEventTime(System.currentTimeMillis());
        processor.process(answer);

        CallEvent answered = ring;
        answered.setEventType(CallEventType.ANSWERED);
        answered.setEventTime(System.currentTimeMillis());
        processor.process(answered);

        CallEvent score = answered;
        score.setEventType(CallEventType.SCORE);
        score.setEventTime(System.currentTimeMillis());
        processor.process(score);

        CallEvent bye = ring;
        bye.setEventType(CallEventType.BYE);
        bye.setEventTime(System.currentTimeMillis());
        processor.process(bye);

//        processor.process(new CallEvent(CallEventType.INCOMING, UUID.randomUUID().toString(), UUID.randomUUID().toString(), System.currentTimeMillis(), "localhost", "2997", "2990"));

//        System.in.read();

        processor.shutdown();
    }
}
