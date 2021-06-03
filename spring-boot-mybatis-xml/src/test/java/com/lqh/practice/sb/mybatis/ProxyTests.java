package com.lqh.practice.sb.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/3 0003 16:14
 * @since 2021/6/3 0003 16:14
 */
public class ProxyTests {


    public static void main(String[] args) {

        // org.apache.ibatis.binding.MapperProxyFactory.newInstance(org.apache.ibatis.binding.MapperProxy<T>)
        IPing pingProxy = (IPing) Proxy.newProxyInstance(
                IPing.class.getClassLoader(),
                new Class[]{IPing.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(method);
                        System.out.println(args + " world");

                        // return what ever you want
                        return null;
                    }
                });

        pingProxy.ping("hello");


    }
}

interface IPing {
    void ping(String content);
}
