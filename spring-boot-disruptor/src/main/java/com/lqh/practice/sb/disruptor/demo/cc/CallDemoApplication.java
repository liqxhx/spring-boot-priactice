package com.lqh.practice.sb.disruptor.demo.cc;

import java.io.IOException;
import java.util.UUID;

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
        CallEventProcessor processor = new CallEventProcessor();

        CallEvent invite = new CallEvent(CallEventType.INVITE, UUID.randomUUID().toString(), UUID.randomUUID().toString(), System.currentTimeMillis(), "localhost", "2997", "2990");
        processor.process(invite);

        invite.setEventType(CallEventType.INCOMING);
        CallEvent incoming = invite;
        processor.process(incoming);

//        processor.process(new CallEvent(CallEventType.INCOMING, UUID.randomUUID().toString(), UUID.randomUUID().toString(), System.currentTimeMillis(), "localhost", "2997", "2990"));

        System.in.read();

    }
}
