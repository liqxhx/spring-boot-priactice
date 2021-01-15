package com.lqh.practice.springboot.mapstruct.utils;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * <p> 类描述: MapStructApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/15 20:55
 * @since 2021/01/15 20:55
 */
@SpringBootApplication
public class MapStructApplication {
    /**
    * main
    */
    public static void main(String[] args){
        new SpringApplicationBuilder().sources(MapStructApplication.class).web(WebApplicationType.NONE).run(args);
    }
}
