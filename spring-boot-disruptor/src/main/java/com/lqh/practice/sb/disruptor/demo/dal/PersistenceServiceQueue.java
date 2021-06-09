package com.lqh.practice.sb.disruptor.demo.dal;

import com.lqh.practice.sb.disruptor.demo.dal.entity.Order;
import com.lqh.practice.sb.disruptor.gettingstart.Printer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * <p> 类描述: PersistenceService
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/08 23:46
 * @since 2021/06/08 23:46
 */
@Slf4j
public class PersistenceServiceQueue {
    private final LinkedList<PersistenceEvent> queue;
    private static volatile boolean running = false;

    private final Thread processThread;
    private final PersistenceHandler persistenceHandler;

    public PersistenceServiceQueue(int handlerSize) {
        this.queue = new LinkedList<>();
        this.persistenceHandler = new PersistenceHandler();
        this.persistenceHandler.regist(Order.class, new OrderDao());
        PersistenceHandler[] handlers = initHandlers(handlerSize);

        this.processThread = new Thread() {
            @Override
            public void run() {
                int size = 0;

                while(PersistenceServiceQueue.running || size > 0) {
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

                    PersistenceEvent e = null;

                    synchronized (queue) {
                        log.info("dequeue: " + size + ", " + e);
                        e = queue.removeFirst();
                        persistenceHandler.persist(e);
                    }

                }
                log.info("processThread exit");
            }
        };
    }

    private PersistenceHandler[] initHandlers(int handlerSize) {
        PersistenceHandler[] handlers = new PersistenceHandler[handlerSize];
        for (int i = 0; i < handlerSize; i++) {
            handlers[i] = new PersistenceHandler();
            handlers[i].regist(Order.class, new OrderDao());
        }
        return handlers;
    }

    public void start() {
        if(PersistenceServiceQueue.running == true) {
            return;
        }

        synchronized (PersistenceServiceQueue.class) {
            if(PersistenceServiceQueue.running == true) {
                return;
            }

            PersistenceServiceQueue.running = true;
        }
        this.processThread.start();
        log.info("started");

    }

    public void stop() {
        if(PersistenceServiceQueue.running == false) {
            return;
        }

        synchronized (PersistenceServiceQueue.class) {
            if(PersistenceServiceQueue.running == false) {
                return;
            }

            PersistenceServiceQueue.running = false;
        }

        log.info("stopped");
    }

    public void persist(int opType, Object entity) {
        StopWatch watch = new StopWatch("persist-produce");
        watch.start();

        if(PersistenceServiceQueue.running) {
            PersistenceEvent persistenceEvent = new PersistenceEvent(opType, entity);
            synchronized (this.queue) {
                this.queue.addLast(persistenceEvent);
                this.queue.notifyAll();
                log.info("enqueue: " + this.queue.size() +", " + persistenceEvent);
            }
        }

        watch.stop();
        Printer.output("produce data:"+ entity + ", " + watch.shortSummary());
    }


    /**
    * main
    */
    public static void main(String[] args) throws InterruptedException {
       PersistenceServiceQueue persistenceService = new PersistenceServiceQueue(10);
       persistenceService.start();

        for (int i = 0; i < 100; i++) {
            persistenceService.persist(PersistenceEvent.OP_INSERT, new Order(i));
        }

        TimeUnit.SECONDS.sleep(60);
    }
}
