package com.lqh.practice.sb.disruptor.demo.dal;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.lqh.practice.sb.disruptor.demo.dal.entity.Order;
import com.lqh.practice.sb.disruptor.gettingstart.Printer;
import org.springframework.util.StopWatch;

import java.util.Optional;

/**
 * <p> 类描述: PersistenceService Use Disruptor
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/08 23:46
 * @since 2021/06/08 23:46
 */
public class PersistenceServiceDisruptor {
    Disruptor<PersistenceEvent> disruptor ;

    public PersistenceServiceDisruptor(int handlerSize) {
        this.disruptor = new Disruptor<>(PersistenceEvent::new, 1024, DaemonThreadFactory.INSTANCE, ProducerType.MULTI, new BlockingWaitStrategy());

        PersistenceHandler[] handlers = initHandlers(handlerSize);

        disruptor.setDefaultExceptionHandler(new PersistenceExceptionHandler());

        disruptor.handleEventsWithWorkerPool(handlers);
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
        Optional.ofNullable(this.disruptor).ifPresent(d -> d.start());
    }

    public void stop() {
        Optional.ofNullable(this.disruptor).ifPresent(d -> d.shutdown());
    }

    public void persist(int opType, Object entity) {
        StopWatch watch = new StopWatch("persist-produce");
        watch.start();
        Optional.ofNullable(this.disruptor).ifPresent(d -> {
            d.publishEvent((event, seq, type, data) -> {
                event.setEntity(data);
                event.setOpType(type);
            }, opType, entity);
        });
        watch.stop();
        Printer.output("produce data:"+ entity + ", " + watch.shortSummary());
    }

    /**
    * main
    */
    public static void main(String[] args){
       PersistenceServiceDisruptor persistenceService = new PersistenceServiceDisruptor(10);
       persistenceService.start();

        for (int i = 0; ; i++) {
            persistenceService.persist(PersistenceEvent.OP_INSERT, new Order(i));
        }


    }
}
