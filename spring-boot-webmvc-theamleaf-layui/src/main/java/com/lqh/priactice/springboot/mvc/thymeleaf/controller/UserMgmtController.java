package com.lqh.priactice.springboot.mvc.thymeleaf.controller;

import com.lqh.priactice.springboot.mvc.thymeleaf.entity.User;
import com.lqh.priactice.springboot.mvc.thymeleaf.enums.Gender;
import com.lqh.priactice.springboot.mvc.thymeleaf.service.UserService;
import com.lqh.priactice.springboot.mvc.thymeleaf.vo.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p> 类描述: UserMgmtController
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/10 16:58
 * @since 2020/07/10 16:58
 */
@Controller
@RequestMapping("/user/mgmt")
public class UserMgmtController {

    @Autowired
    private UserService userService;

    /**
     * regist login 前置action
     * @return
     */
    @GetMapping("/reggin")
    public String userRegistOrLoginPreAction() {
        return "lay/reggin";
    }

    /**
     * regist
     * @param user
     * @return
     */
    @PostMapping(value = "/regist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<User> userRegistAction(@RequestBody User user) {
        return null;
    }

    /**
     * login
     * @param user
     * @return
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<User> userLoginAction(@RequestBody User user) {
        return null;
    }

    @GetMapping("/users")
    public String users() {
        return "lay/users";
    }


//    @PostMapping("/list.ajax")
//    public void list(User userQuery, HttpServletResponse response) {
//        try (PrintWriter printWriter = new PrintWriter(response.getOutputStream())) {
//            printWriter.println();
//            printWriter.flush();
//        } catch (Exception e) {
//
//        }
//    }

    /**
     * /user/mgmt/list
     * @param userQuery
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public ResponseDTO<Collection<User>> list(@RequestBody(required = false) User userQuery) {
       return ResponseDTO.success(userService.list(userQuery), 100);
    }


    @GetMapping("/getlist")
    @ResponseBody
    public ResponseDTO<Collection<User>> getlist(User userQuery) {
        return ResponseDTO.success(userService.list(userQuery), 100);
    }


    @GetMapping("/count")
    @ResponseBody
    public ResponseDTO<Void> count(User userQuery) {
        return ResponseDTO.success(null, 100);
    }

    @GetMapping("/detail/{userId}")
    @ResponseBody
    public ResponseDTO<Void> detail(@PathVariable(name = "userId") Long userId) {
        return ResponseDTO.success(userService.getUserByUserId(userId), 1);
    }

    @GetMapping("/gender")
    public ResponseDTO<Map> gender() {
        return ResponseDTO.success(Arrays.stream(Gender.values()).collect(Collectors.toMap(g -> g.name(), g-> g.getName())));
    }
}
