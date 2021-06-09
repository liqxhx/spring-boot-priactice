package com.lqh.practice.sb.disruptor.gettingstart;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;
import com.lqh.practice.sb.disruptor.gettingstart.LongEvent;
import com.lqh.practice.sb.disruptor.gettingstart.LongEventExceptionHandler;
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
public class MultiHandlers {
    /**
    * main
    */
    public static void main(String[] args) {
        // 创建线程池 用来执行consumer
        ExecutorService executor = Executors.newCachedThreadPool();

        RingBuffer<LongEvent> ringBuffer = RingBuffer.create(ProducerType.MULTI, LongEvent::new, 1024, new BlockingWaitStrategy());

        // 通过RingBuffer创建SequenceBarrier 只有一个实现类：ProcessingSequenceBarrier
        SequenceBarrier barrier = ringBuffer.newBarrier();

        LongEventHandler[] handlers = new LongEventHandler[10];
        for (int i = 0; i < 10; i++) {
            handlers[i] = new LongEventHandler();
        }

        WorkerPool<LongEvent> workerPool = new WorkerPool<>(ringBuffer, barrier, new LongEventExceptionHandler(), handlers);

        // 查看当前工作进度的序列数组
        print(workerPool);

        workerPool.start(executor);

        LongEventProducer2 producer = new LongEventProducer2(ringBuffer);

        // 并发生成5个 LongEvent
        int size = 5;
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

        // 查看当前工作进度的序列数组
        print(workerPool);

        es.shutdown();
        executor.shutdown();
    }

    private static void print(WorkerPool<LongEvent> workerPool) {
        System.out.println(workerPool.getWorkerSequences().length);
        Sequence[] workerSequences = workerPool.getWorkerSequences();
        int m = 0;
        for(Sequence se : workerSequences) {
            System.out.println(m++ +":"+ se.get());
        }
    }
}
