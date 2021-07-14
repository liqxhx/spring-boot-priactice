package com.lqh.practice.sb.disruptor.demo.msg;

/**
 * <p> todo 写点注释吧
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/14 14:18
 * @since 2021/6/14 14:18
 */
public enum MsgSendStrategy {
    MQ_RABBIT {
        @Override
        public MsgSender getSender() {
            return new RabbitMqMsgSender();
        }
    },

    HTTP {
        @Override
        public MsgSender getSender() {
            return new HttpMsgSenderSender();
        }
    };

    public abstract MsgSender getSender();
}
