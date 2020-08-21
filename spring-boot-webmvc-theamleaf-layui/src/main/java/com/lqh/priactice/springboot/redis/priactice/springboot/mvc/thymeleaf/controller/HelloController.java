package com.lqh.priactice.springboot.redis.priactice.springboot.mvc.thymeleaf.controller;

import com.lqh.priactice.springboot.redis.priactice.springboot.mvc.thymeleaf.ex.CommonRuntimeException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/i18n")
    @ResponseBody
    public String i18n() {
        CommonRuntimeException commonRuntimeException = new CommonRuntimeException("e00000", "你好！i18n");
        throw commonRuntimeException;
//        return "hello";
    }

    @GetMapping("/main")
    public String mainPage(){
        return "lay/main";
    }
}
