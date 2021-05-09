package com.lqh.practice.springboot.rmi.sdk;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * <p> 类描述: Client
 *
 * 编译、生成Stub类
 * rmic com.lqh.practice.springboot.rmi.sdk.RemoteUserServiceImpl
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/04 16:18
 * @since 2021/05/04 16:18
 */
public class RemoteUserServiceClient {
    /**
    * main
    */
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        RemoteUserService service = (RemoteUserService) Naming.lookup("rmi://localhost:1099/UserService");
        boolean loginResult  = service.login("liqh", "liqh");
        System.out.println(loginResult);
    }
}
