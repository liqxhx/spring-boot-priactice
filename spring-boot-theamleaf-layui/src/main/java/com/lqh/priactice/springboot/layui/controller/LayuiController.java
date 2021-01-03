package com.lqh.priactice.springboot.layui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p> 类描述: LayuiController
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/12/17 21:58
 * @since 2020/12/17 21:58
 */
@Controller
public class LayuiController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/layout")
    public String layout(){
        return "layout";
    }
}
