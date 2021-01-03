package com.lqh.practice.actuator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p> 类描述: IndexController
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/12/16 20:36
 * @since 2020/12/16 20:36
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
