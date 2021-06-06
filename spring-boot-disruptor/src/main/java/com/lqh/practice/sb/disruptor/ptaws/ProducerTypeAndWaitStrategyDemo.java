package com.lqh.practice.sb.disruptor.ptaws;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lqh.practice.sb.disruptor.gettingstart.LongEvent;
import com.lqh.practice.sb.disruptor.gettingstart.LongEventFactory;
import com.lqh.practice.sb.disruptor.gettingstart.LongEventHandler;
import com.lqh.practice.sb.disruptor.gettingstart.Printer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * <p> 类描述: CountryBanquetApplication
 * 指定ProducerType.MULTI和ProducerType.SINGLE参数
 * 来控制序列器的生成模式。默认使用MULTI模式
 *
 * 如果确认是在单线程环境下产生Event,应该调整为SINGLE模式,可以显著提高性能
 *
 * 如果在多线程情况下使用SINGLE模式,将会导致混乱,出现sequnce丢失问题
 * @author qhlee
 * @version 1.0
 * @date 2021/06/05 21:06
 * @since 2021/06/05 21:06
 */
@SpringBootApplication
public class ProducerTypeAndWaitStrategyDemo implements ApplicationRunner {
    /**
     * main
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ProducerTypeAndWaitStrategyDemo.class)
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
        ThreadFactory tf = Executors.defaultThreadFactory();

        LongEventFactory ef = new LongEventFactory();

        int bufferSize = 4;

        // 默认 ProducerType.MULTI BlockingWaitingStategy
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(ef, bufferSize, tf, ProducerType.MULTI, new YieldingWaitStrategy());
//        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(ef, bufferSize, tf);
//        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(ef, bufferSize, tf, ProducerType.SINGLE, new BlockingWaitStrategy());



        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        int size = 10;
        CyclicBarrier barrier = new CyclicBarrier(size);
        ExecutorService es = Executors.newCachedThreadPool();

        EventTranslatorOneArg translatorOneArg = new EventTranslatorOneArg<LongEvent, Long>() {
            @Override
            public void translateTo(LongEvent event, long sequence, Long arg0) {
                event.set(arg0);
                Printer.output("produce " + event + " " + System.identityHashCode(event) + " with seq " + sequence);
            }
        };

        for (int i = 0; i < size; i++) {
            final int index = i;
            es.submit(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Printer.output(index + " ready");

                            barrier.await();

                            ringBuffer.publishEvent(translatorOneArg, Long.valueOf(index));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            );

        }



//        es.shutdown();
//        disruptor.shutdown();
    }
}
