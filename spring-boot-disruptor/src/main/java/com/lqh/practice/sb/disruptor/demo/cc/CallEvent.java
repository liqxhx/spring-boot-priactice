package com.lqh.practice.sb.disruptor.demo.cc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> CallEvent
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/8 0008 18:25
 * @since 2021/6/8 0008 18:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CallEvent {
    private CallEventType     eventType;
    private String  sessionId;
    private Long    eventTime;
    private String  ip;
    private String  caller;
    private String  callee;
}
