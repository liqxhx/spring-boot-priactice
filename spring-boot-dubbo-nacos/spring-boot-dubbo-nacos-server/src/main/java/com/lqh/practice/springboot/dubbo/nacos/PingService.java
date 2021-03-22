package com.lqh.practice.springboot.dubbo.nacos;

import com.lqh.practice.common.domain.IPing;
import org.apache.dubbo.config.annotation.DubboService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p> 类描述: nacos
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/03/15 23:34
 * @since 2021/03/15 23:34
 */
@DubboService(version = "1.0")
public class PingService implements IPing {
    @Override
    public String ping(String req) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).concat(" pong");
    }
}
