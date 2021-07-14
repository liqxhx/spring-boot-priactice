package com.lqh.practice.sb.disruptor.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.function.Consumer;

/**
 * <p> StopWatchUtils
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/18 14:14
 * @since 2021/6/18 14:14
 */
@Slf4j
public class StopWatchUtils {
    public static <T> void consume(Consumer<T> c, T arg) {
        StopWatch stopWatch = new StopWatch("ConsumeWatch");
        stopWatch.start();
        c.accept(arg);
        stopWatch.stop();
        log.info("consume {} {}", arg,  stopWatch.shortSummary());
    }

    public static <T> void produce(Consumer<T> c, T arg) {
        StopWatch stopWatch = new StopWatch("ProduceWatch");
        stopWatch.start();
        c.accept(arg);
        stopWatch.stop();
        log.info("produce {} {}", arg,  stopWatch.shortSummary());
    }
}
