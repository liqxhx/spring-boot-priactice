package com.lqh.priactice.springboot.redis.priactice.springboot.webflux.config;

import com.lqh.priactice.springboot.redis.priactice.springboot.webflux.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * <p> 类描述: Web Flux Router(s) Configuration
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/08/19 10:53
 * @since 2020/08/19 10:53
 */
@Configuration
public class WebFluxRouterConfiguration {
    @Bean
    public RouterFunction<ServerResponse> helloWorldRouterFunction(HelloHandler helloHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/helloworld"), helloHandler::helloWorld);
    }
}
