package com.lqh.practice.springboot.aop.circuitbreak;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * <p> 类描述: CircuitBreakerAspect
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/02/27 10:34
 * @since 2021/02/27 10:34
 */
@Component
@Aspect
public class CircuitBreakerAspect {

    ExecutorService executorService= Executors.newFixedThreadPool(10);

    @Pointcut(value = "@annotation(com.lqh.practice.springboot.aop.circuitbreak.CircuitBreaker)")
    public void pointCut(){}

    @Around(value = "pointCut()&&@annotation(hystrixCommand)")
    public Object doPointCut(ProceedingJoinPoint joinPoint, CircuitBreaker hystrixCommand) throws InterruptedException, ExecutionException, TimeoutException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int timeout=hystrixCommand.timeout();
        //前置的判断逻辑
        Future future=executorService.submit(()->{
            try {
                return joinPoint.proceed(); //执行目标方法
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        });
        Object result;
        try {
            result=future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            future.cancel(true);
            // ？
            if(!StringUtils.hasText(hystrixCommand.fallback())){
                throw e;
            }
            //调用fallback
            result=invokeFallback(joinPoint,hystrixCommand.fallback());
        }
        return result;
    }

    private Object invokeFallback(ProceedingJoinPoint joinPoint, String fallback) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MethodSignature signature=(MethodSignature)joinPoint.getSignature();
        Method method=signature.getMethod();
        Class<?>[] parameterTypes=method.getParameterTypes();
        //以上是获取被代理的方法的参数和Method
        //得到fallback方法
        try {
            Method fallbackMethod=joinPoint.getTarget().getClass().getMethod(fallback,parameterTypes);
            fallbackMethod.setAccessible(true);
            //完成反射调用
            return fallbackMethod.invoke(joinPoint.getTarget(),joinPoint.getArgs());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
