package com.lqh.priactice.springboot.webflux.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

/**
 * <p> 类描述: HelloHandler
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/08/19 10:56
 * @since 2020/08/19 10:56
 */
@Component
public class HelloHandler {
    public Mono<ServerResponse> helloWorld(ServerRequest serverRequest) {
        return ServerResponse
                .status(HttpStatus.OK)
                .body(Mono.just("hello web flux world"), String.class);
    }
}
