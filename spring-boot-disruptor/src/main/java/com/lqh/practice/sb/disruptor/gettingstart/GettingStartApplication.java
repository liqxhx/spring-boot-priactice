package com.lqh.practice.sb.disruptor.gettingstart;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.nio.ByteBuffer;

/**
 * <p> 类描述: GettingStartApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/05 15:14
 * @since 2021/06/05 15:14
 */
@SpringBootApplication
public class GettingStartApplication implements ApplicationRunner {
    /**
     * main
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(GettingStartApplication.class)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .build()
                .run(args);
    }

    /**
     * <ol>开发步骤
     *     <li> 定义 Event
     *     <li> 实现 {@link EventFactory}
     *     <li> 实现 {@link EventHandler}
     *     <li> 整合
     * </ol>
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
        // todo 自定义线程池
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

        // 指定事件处理器
        // todo 必须在disruptor.start()前，否则报： All event handlers must be added before calling starts
        disruptor.handleEventsWith(new LongEventHandler()).then(new ClearingEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // todo 准备生产数据
        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        // todo 发送事件模板方法
//        for (int i=0;;i++) {
//            // 获取可用序号
//            long seq = ringBuffer.next();
//            try {
//                LongEvent e = ringBuffer.get(seq);
//                e.set(i);
//            } finally {
//                TimeUnit.SECONDS.sleep(1);
//                // 发布序号（关联的事件）
//                ringBuffer.publish(seq);
//            }
//        }

        ByteBuffer bb = ByteBuffer.allocate(8);
        // todo 传统
//        for (long l = 0; true; l++)
//        {
//            bb.putLong(0, l);
//            producer.onData(bb);
//            Thread.sleep(1000);
//        }

        // todo EventTranslator
        LongEventProducerWithTranslator translatorUtil = new LongEventProducerWithTranslator(ringBuffer);
        EventTranslatorOneArg translatorOneArg = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {

            @Override
            public void translateTo(LongEvent event, long sequence, ByteBuffer arg0) {
                long val = arg0.getLong(0);
                Printer.output("\u001b[34mLongEventTranslatorOneArg[seq = "+ sequence + ", value = " + val + "]\u001b[0m");
                event.set(val);
            }
        };

        for (long l = 0; l < 100; l++) {
            bb.putLong(0, l);
//             ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
//            translator.onData(bb); // instantiate an object to hold the ByteBuffer bb variable
            ringBuffer.publishEvent(translatorOneArg, bb);
            Thread.sleep(1000);
        }


        disruptor.shutdown();
    }
}



