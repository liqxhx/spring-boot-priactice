package com.lqh.priactice.spring.security.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> 类描述: MyErrorController
 * https://zhuanlan.zhihu.com/p/43352579
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/21 18:35
 * @since 2020/09/21 18:35
 */
public class MyErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == 401){
            return "/401";
        }else if(statusCode == 404){
            return "/404";
        }else if(statusCode == 403){
            return "/403";
        }else{
            return "/500";
        }

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
