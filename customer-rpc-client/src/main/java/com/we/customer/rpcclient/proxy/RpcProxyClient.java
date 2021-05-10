package com.we.customer.rpcclient.proxy;

import java.lang.reflect.Proxy;

/**
 * Rpc客户端代理
 * @author we
 * @date 2021-05-10 16:56
 **/
public class RpcProxyClient {

    public <T> T clientProxy(final Class<T> interfaceCls,final String host,final int port){
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class<?>[]{interfaceCls},new RemoteInvocationHandler(host,port));
    }

}
