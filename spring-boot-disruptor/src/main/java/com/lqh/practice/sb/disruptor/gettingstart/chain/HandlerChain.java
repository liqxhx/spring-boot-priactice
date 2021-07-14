package com.lqh.practice.sb.disruptor.gettingstart.chain;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.lqh.practice.sb.disruptor.gettingstart.LongEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p> 类描述: HandlerChain
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/09 22:51
 * @since 2021/06/09 22:51
 */
@Slf4j
public class HandlerChain {
    /**
    * main
    */
    public static void main(String[] args){
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, 1024, DaemonThreadFactory.INSTANCE, ProducerType.MULTI, new BlockingWaitStrategy());

        disruptor.handleExceptionsFor(new Handler1()).with(new ExceptionHandler1());
        disruptor.setDefaultExceptionHandler(new DefaultExceptionHandler());

        // 串行 h1 h2 h3 h4
        disruptor.handleEventsWith(new Handler1()).handleEventsWith(new Handler2()).then(new Handler3()).then(new Handler4());

        // 并行
//        disruptor.handleEventsWith(new Handler1(), new Handler2(), new Handler3(), new Handler4());
//        disruptor.handleEventsWith(new Handler1());
//        disruptor.handleEventsWith(new Handler2());
//        disruptor.handleEventsWith(new Handler3());
//        disruptor.handleEventsWith(new Handler4());

        /** 菱形
         *      h2
         * h1         h4
         *      h3
         */

//        disruptor.handleEventsWith(new Handler1()).handleEventsWith(new Handler2(), new Handler3()).handleEventsWith(new Handler4());

        /**
         * h1 h4
         *          h2
         * h3
         */
//        EventHandlerGroup<LongEvent> g1 = disruptor.handleEventsWith(new Handler1()).handleEventsWith(new Handler4());
//        EventHandlerGroup<LongEvent> g2 = disruptor.handleEventsWith(new Handler3());
//        g1.and(g2).handleEventsWith(new Handler2());

        disruptor.start();

        //5线程并发下的Disruptor
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            final int id = i;
            executor.submit(()-> {
                try {
                    disruptor.publishEvent((e, seq, d) -> {
                        e.setContent("");
                        e.set(d);
                        log.debug("produce " + d);
                    }, id);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            });
        }

//        executor.shutdown();
//        disruptor.shutdown();
    }
}
