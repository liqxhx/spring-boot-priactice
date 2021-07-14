package com.lqh.practice.sb.disruptor.demo.msg;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.LinkedList;

/**
 * <p> BlockingQueue 单消费者
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/14 14:28
 * @since 2021/6/14 14:28
 */
@Slf4j
public class MsgSenderServiceLinkedList {
    private final LinkedList<Msg> queue;
    private static volatile boolean running = false;
    private final Thread processThread;
    private final MsgHandler msgHandler;

    public MsgSenderServiceLinkedList() {
        this.queue = new LinkedList<>();
        this.msgHandler = new MsgHandler();

        /**
         * 创建处理线程
         */
        this.processThread = new Thread() {
            @Override
            public void run() {
                int size = 0;

                // running 为true 或者 size不为0 都将继续处理
                while(MsgSenderServiceLinkedList.running || size > 0) {
                    synchronized (queue) {
                        size = queue.size();
                        if(size == 0) {
                            log.warn("queue empty wait 1s");
                            try {
                                queue.wait(1000);
                            } catch (InterruptedException e) {
                                log.error(e.getMessage(), e);
                            }
                            continue;
                        }
                        size = queue.size();
                    }

                    Msg e = null;

                    synchronized (queue) {
                        log.info("dequeue: " + size + ", " + e);
                        e = queue.removeFirst();
                        msgHandler.onMsg(e);
                    }
                }
                log.info("processThread exit");
            }
        };
    }

    public void start() {
        if(MsgSenderServiceLinkedList.running == true) {
            return;
        }

        synchronized (MsgSenderServiceLinkedList.class) {
            if(MsgSenderServiceLinkedList.running == true) {
                return;
            }
            MsgSenderServiceLinkedList.running = true;
        }
        this.processThread.start();
        log.info("started");

    }

    public void stop() throws InterruptedException {
        if(MsgSenderServiceLinkedList.running == false) {
            return;
        }

        synchronized (MsgSenderServiceLinkedList.class) {
            if(MsgSenderServiceLinkedList.running == false) {
                return;
            }

            MsgSenderServiceLinkedList.running = false;
        }

        if (processThread != null) {
            processThread.join();
        }

        log.info("stopped");
    }

    public void persist(long id, long time, String content) {
        StopWatch watch = new StopWatch("persist-produce");
        watch.start();

        if(MsgSenderServiceLinkedList.running) {
            Msg msg = new Msg(id, time, content);
            synchronized (this.queue) {
                this.queue.addLast(msg);
                this.queue.notifyAll();
                log.info("enqueue: " + this.queue.size() +", " + msg);
            }
        }

        watch.stop();
        log.info("produce {} {}", content, watch.shortSummary());
    }

    @Test
    public void test() throws InterruptedException {
        MsgSenderServiceLinkedList service = new MsgSenderServiceLinkedList();
        service.start();

        for (int i = 0; i < 100; i++) {
            service.persist(Long.valueOf(i), System.nanoTime(), "test-"+i);
        }

        service.stop();

    }
}
