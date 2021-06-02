package com.lqh.practice.springboot.retry.controller;

import com.lqh.practice.springboot.retry.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 类描述: RetryController
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/16 11:51
 * @since 2021/01/16 11:51
 */
@RestController
@Slf4j
public class RetryController {
    @Autowired
    private PayService payService;

    /**
     * 
     * @param num
     * @return
     * @throws Exception
     */
    @GetMapping("/createOrder")
    public String createOrder(@RequestParam int num) throws Exception{
        int remainingnum = payService.minGoodsnum(num);
        log.info("剩余的数量==="+remainingnum);
        return "库库存成功";
    }
}
