package com.lqh.practice.sb.disruptor.gettingstart;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * <p> 类描述: GettingStartApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/05 15:14
 * @since 2021/06/05 15:14
 */
@Slf4j
public class GettingStartApplication {

    /**
     * <ol>开发步骤
     *     <li> 定义 Event
     *     <li> 实现 {@link EventFactory}
     *     <li> 实现 {@link EventHandler}
     *     <li> 整合
     * </ol>
     * @throws Exception
     */
    @Test
    public void run() throws Exception {
        // 创建 LongEventFactory
        LongEventFactory factory = new LongEventFactory();

        // 声明buffer大小，必须是2的n次冥，如果不是创建RingBuffer时会报错：bufferSize must be a power of 2
        int bufferSize = 8;

        // 创建 Disruptor，使用Disruptor可以简单化开发
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

        // 指定事件处理器
        // todo 必须在disruptor.start()前，否则报： All event handlers must be added before calling starts
//        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.handleEventsWith(new LongEventHandler()).then(new ClearingEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // 通过disruptor 获取ringbuffer, 用ringbuffer发布数据
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // 原始api  发送事件模板方法
        for (int i=0; i < 100; i++) {
            // 获取可用序号
            long seq = ringBuffer.next();
            try {
                LongEvent e = ringBuffer.get(seq);
                e.set(i);
                if(i == 0) {
                    e.setContent("hello");
                }
                log.debug("produce {}", i);
            } finally {
                // TimeUnit.SECONDS.sleep(1);
                // 发布序号（关联的事件）
                ringBuffer.publish(seq);
            }
        }

//         ByteBuffer bb = ByteBuffer.allocate(8);

        // 传统api
//        LongEventProducer producer = new LongEventProducer(ringBuffer);
//        for (long l = 0; l < 100L ; l++) {
//            bb.putLong(0, l);
//            producer.onData(bb);
//        }

        // EventTranslator
//        LongEventProducerWithTranslator translatorUtil = new LongEventProducerWithTranslator(ringBuffer);
//        EventTranslatorOneArg translatorOneArg = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
//
//            @Override
//            public void translateTo(LongEvent event, long sequence, ByteBuffer arg0) {
//                long val = arg0.getLong(0);
//                Printer.output("\u001b[34mLongEventTranslatorOneArg[seq = "+ sequence + ", value = " + val + "]\u001b[0m");
//                event.set(val);
//            }
//        };

        // java8 lambda
//        for (long l = 0; l < 100L ; l++) {
//            bb.putLong(0, l);
//            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
//            translatorUtil.onData(bb); // instantiate an object to hold the ByteBuffer bb variable
//            ringBuffer.publishEvent(translatorOneArg, bb);
//            Thread.sleep(1000);
//        }


        disruptor.shutdown();
    }
}



