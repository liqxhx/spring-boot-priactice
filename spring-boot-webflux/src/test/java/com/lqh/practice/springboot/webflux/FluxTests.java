package com.lqh.practice.springboot.webflux;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * <p> 类描述: FluxTests
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/23 20:14
 * @since 2021/04/23 20:14
 */
public class FluxTests {
    /**
     *        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6);
     *         Mono<Integer> mono = Mono.just(1);
     *
     *         // 通过数组的方式创建Flux
     *         Integer[] integers = {1, 2, 3, 4, 5, 6};
     *         Flux<Integer> arrayFlux = Flux.fromArray(integers);
     *
     *         // 通过集合创建Flux
     *         List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
     *         Flux<Integer> listFlux = Flux.fromIterable(list);
     *
     *         // 通过Flux创建另一个Flux
     *         Flux<Integer> fluxFlux = Flux.from(flux);
     *
     *         // 通过流创建Flux
     *         Flux<Integer> streamFlux = Flux.fromStream(Stream.of(1, 2, 3, 4, 5, 6));
     */
    @Test
    public void testReactor() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6);
//        flux.subscribe(
//                System.out::println,
//                System.err::println,
//                () -> System.out.println("complete"),
//                subscription -> {subscription.request(3);});
//
//
//        flux.subscribe(new CustSubscriber());


//        flux.map(i -> i*3).subscribe(System.out::println);

        Integer[] integers = {7, 8, 9};
        Flux<Integer> arrayFlux = Flux.fromArray(integers);

        arrayFlux.flatMap(i -> flux).subscribe(System.out::println);

    }

    class CustSubscriber extends BaseSubscriber<Integer> {
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("subscribe");
            subscription.request(1);
        }

        @Override
        protected void hookOnNext(Integer value) {
            if(value.intValue() == 4) cancel();
            System.out.println(value);
            request(1);

        }
    }
}
