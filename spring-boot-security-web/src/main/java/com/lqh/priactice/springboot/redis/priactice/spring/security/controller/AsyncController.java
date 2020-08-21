package com.lqh.priactice.springboot.redis.priactice.spring.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p> 类描述: AsyncController
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/10 08:02
 * @since 2020/07/10 08:02
 */
@RestController
@Slf4j
public class AsyncController {
    private static final LinkedBlockingQueue<DeferredResult<String>> queue = new LinkedBlockingQueue();

    @GetMapping("/ping")
    public String ping() throws InterruptedException {
        log.debug("main start");
        Thread.sleep(1000);
        log.debug("main return");
        return System.currentTimeMillis() + " pong";
    }

    /**
     * Callable实现异步请求
     * @return
     */
    @GetMapping("/ping/callable")
    public Callable<String> callable() {
        log.debug("main start");
        Callable<String> ret = () -> {
            log.debug("callable start");
            Thread.sleep(1000);
            log.debug("callable return");
            return System.currentTimeMillis() + " pong in callable";
        };
        log.debug("main return");
        return ret;
    }

    @GetMapping("/ping/defered")
    public DeferredResult<String> deferedResult() {
        DeferredResult<String> result = new DeferredResult<>(50L);
        queue.offer(result);
        result.onTimeout(()->println("超时"));
        result.onCompletion(()->println("完成"));
        result.onError((e)->println("出錯了:"+e.getMessage()));
        return result;
    }

    @GetMapping("/completion-stage")
    public CompletionStage<String> completionStage(){
        final long startTime = System.currentTimeMillis();

        println("Hello,World");

        return CompletableFuture.supplyAsync(()->{
            long costTime = System.currentTimeMillis() - startTime;
            println("执行计算结果，消耗：" + costTime + " ms.");
            return "Hello,World"; // 异步执行结果
        });
    }

    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("AsyncController[" + threadName + "]: " + object);
    }

    // 超时随机数
    private final Random random = new Random();
    @Scheduled(fixedRate = 50L)
    public void process() throws InterruptedException { // 定时操作
        log.info("process......");
        DeferredResult<String> result = null;
        do {
            // 阻塞
            result = queue.take();
            // 随机超时时间
            long timeout = random.nextInt(100);
            // 模拟等待时间，RPC 或者 DB 查询
            Thread.sleep(timeout);
            // 计算结果
            result.setResult(RandomStringUtils.randomAlphanumeric(8));
            println("执行计算结果，消耗：" + timeout + " ms.");
        } while (result != null);
    }
}
