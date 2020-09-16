package com.lqh.priactice.spring.security.controller;

import com.lqh.priactice.spring.security.dto.ResponseDTO;
import com.lqh.priactice.spring.security.service.PingService;
import com.sun.deploy.pings.Pings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p> 类描述: PingController
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/10 17:08
 * @since 2020/09/10 17:08
 */
@RestController
@Slf4j
public class PingController {
    @Resource
    private PingService pingService;

    @GetMapping("/ping")
    public ResponseDTO ping() {
        log.debug("#controller#ping");
        pingService.ping();
        log.info("#controller#pong");
        return ResponseDTO.success("success");
    }

    @GetMapping("/ping2")
    public ResponseDTO pingWithResult() {
        log.debug("#controller#ping2");
         Object result = pingService.pingWithResult();
        log.info("#controller#pong2");
        return ResponseDTO.success("success"+result);
    }
}
