package com.lqh.priactice.actuator.controller;

import com.lqh.priactice.actuator.dto.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 类描述: PingController
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/12/16 20:13
 * @since 2020/12/16 20:13
 */
@RestController
@RequestMapping("/ping")
public class PingController {
    @GetMapping("/now")
    public ResponseDTO<Long> now() {
        return ResponseDTO.success(System.currentTimeMillis());
    }
}
