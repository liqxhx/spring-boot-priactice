package com.lqh.practice.springboot.rmi.sdk;

import com.lqh.practice.common.domain.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * <p> 类描述: RemoteUserService
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/04 10:27
 * @since 2021/05/04 10:27
 */
public interface RemoteUserService extends Remote {
    boolean login(String username, String password)  throws RemoteException;
    User create(String username, String password) throws RemoteException;
}
