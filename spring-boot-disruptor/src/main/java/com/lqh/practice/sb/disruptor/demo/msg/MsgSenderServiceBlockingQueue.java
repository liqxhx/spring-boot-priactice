package com.lqh.practice.sb.disruptor.demo.msg;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p> BlockingQueue 单消费者
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/14 14:28
 * @since 2021/6/14 14:28
 */
@Slf4j
public class MsgSenderServiceBlockingQueue {
    volatile AtomicBoolean running;
    BlockingQueue<Msg> queue;
    ExecutorService executorService;
    MsgHandler handler;

    public MsgSenderServiceBlockingQueue() {
        this.handler = new MsgHandler();

        this.executorService = Executors.newSingleThreadExecutor();

        this.running = new AtomicBoolean(false);

        this.queue = new ArrayBlockingQueue(1024);

        log.debug("started {}", this);
    }

    public void start() {
        if(this.running != null && this.running.compareAndSet(false, true)) {

            Runnable processTask = () -> {
                int size = this.queue.size();

                while(this.running.get() || size > 0) {
                    size = this.queue.size();
                    try {
                        Msg msg = this.queue.poll(1, TimeUnit.SECONDS);
                        log.info("dequeue {} {}", msg, size);
                        if(msg == null) {
                            log.warn("queue empty");
                            continue;
                        }

                        handler.onMsg(msg);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                    }
                }
                log.info("处理线程结束");
            };

            Optional.ofNullable(this.executorService).ifPresent(es -> es.submit(processTask));
        } else {
            log.warn("already started");
        }

    }

    public void stop() {
        if(this.running != null && this.running.compareAndSet(true, false)) {

            Optional.ofNullable(this.executorService).ifPresent(es -> {
                es.shutdown();
                log.info("stopped");
            });

        } else {
            log.warn("already stopped or not started");
        }
    }

    public boolean send(Msg msg) {
        if (this.queue != null && this.running != null && this.running.get()) {
            try {
                boolean ret =  this.queue.offer(msg, 2, TimeUnit.SECONDS);
                log.info("enqueue {} {}", msg, this.queue.size());
                return ret;
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }
        return false;
    }

    @Test
    public void test() {
        MsgSenderServiceBlockingQueue service = new MsgSenderServiceBlockingQueue();
        service.start();
//        service.start();

        for (int i = 0; i < 100; i++) {
            service.send(new Msg(Long.valueOf(i), System.nanoTime(), "test-" + i));
        }

        service.stop();
//        service.stop();
    }
}
