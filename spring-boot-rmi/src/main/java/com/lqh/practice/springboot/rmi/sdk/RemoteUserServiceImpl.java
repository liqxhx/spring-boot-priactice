package com.lqh.practice.springboot.rmi.sdk;

import com.lqh.practice.common.domain.User;
import com.lqh.practice.springboot.rmi.service.UserService;
import com.lqh.practice.springboot.rmi.service.UserServiceImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * <p> 类描述: RemoteUserServiceImpl
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/04 15:56
 * @since 2021/05/04 15:56
 */
public class RemoteUserServiceImpl extends UnicastRemoteObject implements RemoteUserService {
    private UserService userService = new UserServiceImpl();

    public RemoteUserServiceImpl() throws RemoteException {
        super();
    }

    public RemoteUserServiceImpl(int port) throws RemoteException {
        super(port);
    }

    public RemoteUserServiceImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public boolean login(String username, String password) throws RemoteException  {
        return userService.login(username, password);
    }

    @Override
    public User create(String username, String password) throws RemoteException {
        return userService.create(username, password);
    }

    /**
    * main
    */
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        LocateRegistry.createRegistry(1099);
        Naming.bind("rmi://localhost:1099/UserService", new RemoteUserServiceImpl());
        System.out.println("Server Started");
    }
}
