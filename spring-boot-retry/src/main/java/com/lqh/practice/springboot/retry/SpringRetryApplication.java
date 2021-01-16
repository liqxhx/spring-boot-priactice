package com.lqh.practice.springboot.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * <pre> 类描述: SpringRetryApplication
 * 参考：https://www.toutiao.com/i6881604908940788228/
 *
 * spring-retry 工具虽能优雅实现重试，但是存在两个不友好设计：
 *
 * 一个是重试实体限定为 Throwable 子类，说明重试针对的是可捕捉的功能异常为设计前提的，但是我们希望依赖某个数据对象实体作为重试实体， 但 sping-retry框架必须强制转换为Throwable子类。
 *
 * 另一个就是重试根源的断言对象使用的是 doWithRetry 的 Exception 异常实例，不符合正常内部断言的返回设计。
 *
 * Spring Retry 提倡以注解的方式对方法进行重试，重试逻辑是同步执行的，重试的“失败”针对的是Throwable， 如果你要以返回值的某个状态来判定是否需要重试，可能只能通过自己判断返回值然后显式抛出异常了。
 *
 * @Recover 注解在使用时无法指定方法，如果一个类中多个重试方法，就会很麻烦。
 * </pre>
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/16 11:41
 * @since 2021/01/16 11:41
 */
@SpringBootApplication
@EnableRetry // 在主类上加上@EnableRetry注解，表示启用重试机制
public class SpringRetryApplication {
    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run(SpringRetryApplication.class,args);
    }
}
