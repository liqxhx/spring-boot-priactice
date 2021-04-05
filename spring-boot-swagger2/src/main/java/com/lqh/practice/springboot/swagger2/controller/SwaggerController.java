package com.lqh.practice.springboot.swagger2.controller;

import com.lqh.practice.springboot.swagger2.model.Request;
import com.lqh.practice.springboot.swagger2.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p> 类描述: SwaggerController
 * @Api：修饰整个类，描述Controller的作用
 * @ApiOperation：描述一个类的一个方法，或者说一个接口
 * @ApiParam：单个参数描述
 * @ApiModel：用对象来接收参数
 * @ApiProperty：用对象接收参数时，描述对象的一个字段
 * @ApiResponse：HTTP响应其中1个描述
 * @ApiResponses：HTTP响应整体描述
 * @ApiIgnore：使用该注解忽略这个API
 * @ApiError ：发生错误返回的信息
 * @ApiImplicitParam：描述一个请求参数，可以配置参数的中文含义，还可以给参数设置默认值
 * @ApiImplicitParams：描述由多个 @ApiImplicitParam 注解的参数组成的请求参数列表
 *
 * 作者：大鱼炖海棠
 * 链接：https://www.jianshu.com/p/c79f6a14f6c9
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @author qhlee
 * @version 1.0
 * @date 2021/04/02 21:36
 * @since 2021/04/02 21:36
 */
@RestController
@RequestMapping(value="/swagger")
//@Api(description = "生产者进程API接口 SwaggerController")
@Api(tags = "Swagger测试")
@Slf4j
public class SwaggerController {
    @GetMapping
    public String ping(@ApiParam @RequestBody String text) {
        return "pong " + text;
    }

    @ApiOperation(value="查询单词计数", notes="查询单词计数", produces="application/json")
    @ApiImplicitParam(name = "word", value = "单词", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value="/queryWordCount", produces={ MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes={ MediaType.APPLICATION_JSON_UTF8_VALUE }, method=RequestMethod.POST)
    public String queryWordCount(@RequestBody String word) {
        log.info("查询单词计数：{}", word);
        return "SUCESS";
    }

    @PostMapping("/req1")
    @ApiOperation(value = "aaaaaaa", notes = "req1方法", produces = "application/json")
    public Response<String> req1(@RequestBody Request<String> request) {
        return Response.success();
    }


    @PostMapping("/req2/{id}")
    @ApiOperation(value="req2方法", produces="application/json")
    public Response<String> req2(@PathVariable(name = "id") @ApiIgnore String id) {
        return Response.success();
    }
}