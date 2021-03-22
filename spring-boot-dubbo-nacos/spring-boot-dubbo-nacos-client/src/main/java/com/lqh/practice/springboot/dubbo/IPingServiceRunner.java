package com.lqh.practice.springboot.dubbo;

import com.lqh.practice.common.domain.IPing;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * <p> 类描述: IPingServiceRunner
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/03/15 23:53
 * @since 2021/03/15 23:53
 */
@Component
public class IPingServiceRunner implements ApplicationRunner {
    @DubboReference(version = "1.0")
    public IPing ping;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(ping.ping("hi"));
    }
}
