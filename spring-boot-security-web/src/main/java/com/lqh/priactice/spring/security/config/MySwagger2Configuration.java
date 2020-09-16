package com.lqh.priactice.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p> 类描述: Swagger2Configuration
 *
 * <p>
 *     Api：修饰整个类，描述Controller的作用
 * ApiOperation：描述一个类的一个方法，或者说一个接口
 * ApiParam：单个参数描述
 * ApiModel：用对象来接收参数
 * ApiProperty：用对象接收参数时，描述对象的一个字段
 * ApiResponse：HTTP响应其中1个描述
 * ApiResponses：HTTP响应整体描述
 * ApiIgnore：使用该注解忽略这个API
 * ApiError ：发生错误返回的信息
 * ApiImplicitParam：一个请求参数
 * ApiImplicitParams：多个请求参数
 * </p>
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/05 12:14
 * @since 2020/09/05 12:14
 */
@EnableSwagger2
@Configuration
public class MySwagger2Configuration {
    /**
     * @EnableSwagger2注解启用Swagger2，
     * 然后配置一个Docket Bean
     * 配置映射路径和要扫描的接口的位置，在apiInfo中，
     * 主要配置一下Swagger2文档网站的信息，
     * 例如网站的title，网站的描述，联系人的信息，使用的协议等等。
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("标题：spring security practice")
                        .description("描述：spring security practice")
                        .contact(new Contact("lqh","www.baidu.com","lqh@xx.com"))
                        .version("版本号:1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lqh.priactice.spring.security.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
