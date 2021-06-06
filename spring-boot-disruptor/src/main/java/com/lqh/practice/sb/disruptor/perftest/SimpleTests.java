package com.lqh.practice.sb.disruptor.perftest;


import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.Sequencer;
import com.lmax.disruptor.YieldingWaitStrategy;
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
public class SimpleTests {
    //待生产的对象个数
    final long objCount = 1000000;

    public void testBlockingQueue() throws Exception {
        final LinkedBlockingQueue<TestObj> queue = new LinkedBlockingQueue<>();

        //生产者
        Thread producer = new Thread(() -> {

            try {
                for (long i = 1; i <= objCount; i++) {
                    //生产
                    queue.put(new TestObj(i));
                }
            } catch (InterruptedException e) {
            }
        });

        //消费者
        Thread consumer = new Thread(() -> {
            try {
                TestObj readObj = null;
                for (long i = 1; i <= objCount; i++) {
                    //消费
                    readObj = queue.take();
                    //DoSomethingAbout(readObj);
                }
            } catch (InterruptedException e) {
            }
        });

        StopWatch stopWatch = new StopWatch("testBlockingQueue");
        stopWatch.start("main");

        producer.start();
        consumer.start();
        consumer.join();
        producer.join();

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }


    //使用RingBuffer测试
    public void testRingBuffer() throws Exception {
        //创建一个单生产者的RingBuffer，EventFactory是填充缓冲区的对象工厂
        //            YieldingWaitStrategy等"等待策略"指出消费者等待数据变得可用前的策略
        final RingBuffer<TestObj> ringBuffer = RingBuffer.createSingleProducer(TestObj::new, 1024, new YieldingWaitStrategy());
        //创建消费者指针
        final SequenceBarrier barrier = ringBuffer.newBarrier();


        //生产者
        Thread producer = new Thread(() -> {
            for (long i = 1; i <= objCount; i++) {
                ringBuffer.publishEvent((e, s, v) -> e.setValue(v), i);
            }
        });

        Thread consumer = new Thread(() -> {
            TestObj readObj = null;
            int readCount = 0;
            long readIndex = Sequencer.INITIAL_CURSOR_VALUE;

            //读取objCount个元素后结束
            while (readCount < objCount) {
                try {
                    //当前读取到的指针+1，即下一个该读的位置
                    long nextIndex = readIndex + 1;

                    //等待直到上面的位置可读取
                    long availableIndex = barrier.waitFor(nextIndex);

                    //从下一个可读位置到目前能读到的位置(Batch!)
                    while (nextIndex <= availableIndex) {
                        //获得Buffer中的对象
                        readObj = ringBuffer.get(nextIndex);
                        //DoSomethingAbout(readObj);
                        readCount++;
                        nextIndex++;
                    }
                    readIndex = availableIndex;//刷新当前读取到的位置
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        StopWatch stopWatch = new StopWatch("testRingBuffer");
        stopWatch.start();

        producer.start();
        consumer.start();
        consumer.join();
        producer.join();

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

    }

    /**
     * main
     */
    public static void main(String[] args) throws Exception {

        new SimpleTests().testBlockingQueue();

        new SimpleTests().testRingBuffer();
    }
}
