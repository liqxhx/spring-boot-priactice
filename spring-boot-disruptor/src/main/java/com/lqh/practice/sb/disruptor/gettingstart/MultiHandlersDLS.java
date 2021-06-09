package com.lqh.practice.sb.disruptor.gettingstart;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.lqh.practice.sb.disruptor.gettingstart.LongEvent;
import com.lqh.practice.sb.disruptor.gettingstart.LongEventHandler;
import com.lqh.practice.sb.disruptor.gettingstart.LongEventProducer2;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p> 类描述: MultiHandlers
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/09 21:12
 * @since 2021/06/09 21:12
 */
@Slf4j
public class MultiHandlersDLS {
    /**
    * main
    */
    public static void main(String[] args) {
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, 1024, DaemonThreadFactory.INSTANCE, ProducerType.MULTI, new BlockingWaitStrategy());

        int size = 5;
        LongEventHandler[] handlers = new LongEventHandler[size];
        for (int i = 0; i < size; i++) {
            handlers[i] = new LongEventHandler();
        }

        disruptor.handleEventsWithWorkerPool(handlers);

        disruptor.start();

        LongEventProducer2 producer = new LongEventProducer2(disruptor.getRingBuffer());

        // 并发生成5个 LongEvent
        CyclicBarrier cb  = new CyclicBarrier(size);
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < size; i++) {
            int data = i;
            es.submit(() -> {
                try {
                    log.debug("{} ready", data);
                    cb.await();
                    producer.onData(Long.valueOf(data));
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });

        }

        es.shutdown();
    }
}
