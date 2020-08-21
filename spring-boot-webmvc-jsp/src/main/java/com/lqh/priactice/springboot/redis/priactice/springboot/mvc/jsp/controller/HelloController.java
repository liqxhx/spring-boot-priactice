package com.lqh.priactice.springboot.redis.priactice.springboot.mvc.jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p> 类描述: HelloController
 *
 * @author liqxhx
 * @version 1.0
 * @date 2020/03/15 15:19
 * @since 2020/03/15 15:19
 */
@Controller
public class HelloController {
    /**
     * curl -H "Content-Type:text/html;charset=utf-8" http://localhost:8080
     * @param value
     * @param model
     * @return
     */
    @RequestMapping("")
    public String index(@RequestParam(required = false,defaultValue = "0") int value, Model model) {
        model.addAttribute("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "index";
    }
}
