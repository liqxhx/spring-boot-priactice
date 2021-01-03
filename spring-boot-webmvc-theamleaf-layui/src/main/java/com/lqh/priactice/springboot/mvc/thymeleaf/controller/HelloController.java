package com.lqh.priactice.springboot.mvc.thymeleaf.controller;

import com.lqh.priactice.springboot.mvc.thymeleaf.entity.User;
import com.lqh.priactice.springboot.mvc.thymeleaf.ex.CommonRuntimeException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @PostMapping(value = "/hi", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public String hiUser(@RequestParam(value = "userId", required = false) Long userId, User user) {
        return "userId:"+userId + "@=user:" + user.toString();
    }

    @PostMapping(value = "/hi")
    @ResponseBody
    public String hiUser2(@RequestParam(value = "userId", required = false) Long userId, @RequestBody User user) {
        return "userId:"+userId + "@=user:" + user.toString();
    }
}
