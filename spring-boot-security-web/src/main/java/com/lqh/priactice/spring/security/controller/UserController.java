package com.lqh.priactice.spring.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lqh.priactice.spring.security.model.User;
import com.lqh.priactice.spring.security.service.UserService;
import com.lqh.priactice.spring.security.validate.GroupAdd;
import com.lqh.priactice.spring.security.validate.ValidateException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p> 类描述: UserController
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/08 07:47
 * @since 2020/07/08 07:47
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("查询所有用户")
    @GetMapping("/get")
    @JsonView(User.UserSimpleView.class)
    public List<User> get(User queryCondition) {
        return null;
    }

    // 只接收数字
    @GetMapping("/get/{userId:\\d+}")
    @JsonView(User.UserDetailView.class)
    public List<User> get(@PathVariable(name = "userId") Integer userId) {
        return null;
    }

    @PostMapping("/add")
    @JsonView(User.UserDetailView.class)
    public User add(@Validated(GroupAdd.class) @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(System.out::println);
            StringBuffer stringBuffer = new StringBuffer();
            bindingResult.getAllErrors().stream().forEach(e -> {
                FieldError fe = (FieldError)e;
                stringBuffer.append(fe.getField() + ": " + fe.getDefaultMessage()).append(";");
            });
            throw new ValidateException(stringBuffer.toString());
        }
        return userService.save(user);
    }

    @PostMapping("/add2")
    @JsonView(User.UserDetailView.class)
    public User add2(@Validated(GroupAdd.class) @RequestBody User user) {

        return userService.save(user);
    }
}
