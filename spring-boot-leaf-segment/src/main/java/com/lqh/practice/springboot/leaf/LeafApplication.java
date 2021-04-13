package com.lqh.practice.springboot.leaf;

import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.plugin.annotation.EnableLeafServer;
import com.sankuai.inf.leaf.service.SegmentService;
import com.sankuai.inf.leaf.service.SnowflakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p> 类描述: LeafApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/14 00:19
 * @since 2021/04/14 00:19
 */
//EnableLeafServer 开启leafserver
@SpringBootApplication
@EnableLeafServer
public class LeafApplication implements ApplicationRunner {
    @Autowired
    private SegmentService segmentService;
//    @Autowired
//    private SnowflakeService snowflakeService;


    public static void main(String[] args) {
        SpringApplication.run(LeafApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 100000; i++) {
            Result r = segmentService.getId("leaf-segment-test");
            System.out.println(r.getId());
        }

    }
}

