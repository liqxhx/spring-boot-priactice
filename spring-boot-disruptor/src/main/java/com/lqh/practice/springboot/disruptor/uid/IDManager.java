package com.lqh.practice.springboot.disruptor.uid;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;
import com.lqh.practice.springboot.disruptor.IdSegmentConsumer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;

/**
 * <p> 类描述: IdManager
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/15 20:40
 * @since 2021/04/15 20:40
 */
@Component
public class IDManager {

    @PostConstruct
    public void start() {
       RingBuffer ringBuffer = RingBuffer.create(ProducerType.MULTI, IDFactory::new, 2, new YieldingWaitStrategy());

        SequenceBarrier barrier = ringBuffer.newBarrier();

        IdSegmentConsumer[] cs = new IdSegmentConsumer[2];
        for (int i = 0; i < 2; i++) {
            cs[i] = new IdSegmentConsumer(i);
        }

        WorkerPool<ID> workerPool = new WorkerPool<ID>(
                ringBuffer,
                barrier,
                new IDExceptionHander(),
                cs);

        //5 设置多个消费者的sequence序号 用于单独统计消费进度, 并且设置到ringbuffer中
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());

        //6 启动workerPool
        workerPool.start(Executors.newFixedThreadPool(5));
    }

    public void request() {

    }

    public Long next() {

    }
}
