package com.lqh.practice.sb.disruptor.perftest;


import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.Sequencer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lqh.practice.sb.disruptor.gettingstart.LongEvent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.core.annotation.Order;
import org.springframework.util.StopWatch;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p> 类描述: SimpleTests
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/06 12:06
 * @since 2021/06/06 12:06
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName(SimpleTests.NAME)
@Slf4j
public class SimpleTests {
    public static final String NAME = "RingBuffer与BlockingQueue 性能简单测试";
    // 待生产的对象个数
    final long eventCount = 1000000;
    // 统计
    static StopWatch stopWatch;

    @BeforeAll
    public static void ready() {
        stopWatch = new StopWatch(SimpleTests.NAME);
    }

    @AfterAll
    public static void stop() {
        log.info(stopWatch.prettyPrint());
    }

    @Test
    @Order(1)
    @DisplayName("BlockingQueue测试")
    public void testBlockingQueue() throws Exception {
        final LinkedBlockingQueue<LongEvent> queue = new LinkedBlockingQueue<>();

        //生产者
        Thread producer = new Thread(() -> {
            try {
                for (long i = 1; i <= eventCount; i++) {
                    //生产
                    queue.put(new LongEvent(i));
                }
            } catch (InterruptedException e) {
            }
        });

        //消费者
        Thread consumer = new Thread(() -> {
            try {
                LongEvent event = null;
                for (long i = 1; i <= eventCount; i++) {
                    //消费
                    event = queue.take();
                    //DoSomethingAbout(readObj);
                }
            } catch (InterruptedException e) {
            }
        });

        stopWatch.start("BlockingQueue");
        producer.start();
        consumer.start();

        consumer.join();
        producer.join();

        stopWatch.stop();
    }

    //使用RingBuffer测试
    @Test
    @Order(2)
    @DisplayName("RingBuffer测试")
    public void testRingBuffer() throws Exception {
        //创建一个单生产者的RingBuffer
        final RingBuffer<LongEvent> ringBuffer = RingBuffer.createSingleProducer(LongEvent::new, 1024, new YieldingWaitStrategy());
        //创建消费者 barrier
        final SequenceBarrier barrier = ringBuffer.newBarrier();

        //生产者
        Thread producer = new Thread(() -> {
            for (long i = 1; i <= eventCount; i++) {
                // EventTranslatorOneArg
                ringBuffer.publishEvent((e, s, v) -> e.set(v), i);
            }
        });

        Thread consumer = new Thread(() -> {
            LongEvent event = null;

            // readIndex 初始值为 -1
            long readCount = 0;
            long readIndex = Sequencer.INITIAL_CURSOR_VALUE;

            // 读 [0, objCount)
            for(; readCount < eventCount;) {
                try {
                    // 从0开始读
                    long nextIndex = readIndex + 1;

                    // 请求 读nextIndex
                    long availableIndex = barrier.waitFor(nextIndex);

                    // [nextIndex, availableIndex] 可读取
                    for (; nextIndex <= availableIndex; nextIndex++, readCount++) {
                        //获得Buffer中的对象
                        event = ringBuffer.get(nextIndex);
                        //DoSomethingAbout(readObj);
                    }

                    readIndex = availableIndex;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            log.info("readCount:{}, readIndex:{}" , readCount, readIndex);
        });

        stopWatch.start("RingBuffer");
        producer.start();
        consumer.start();
        consumer.join();
        producer.join();
        stopWatch.stop();
    }
}
