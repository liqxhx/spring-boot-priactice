package com.lqh.practice.springboot.swagger2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p> 类描述: SampleController
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/02 21:29
 * @since 2021/04/02 21:29
 */
@Controller
public class SampleController {
    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(ModelMap map) {
        // 设置属性
        map.addAttribute("name", "idig8");
        // testThymeleaf：为模板文件的名称
        // 对应src/main/resources/templates/testThymeleaf.html
        return "testThymeleaf";
    }
}
