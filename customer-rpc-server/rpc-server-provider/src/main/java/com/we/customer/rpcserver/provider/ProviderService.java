package com.we.customer.rpcserver.provider;

import com.we.customer.rpcserver.api.IHelloService;
import com.we.customer.rpcserver.provider.tcp.RpcProxyServer;
import com.we.customer.rpcserver.provider.tcp.RpcProxyServerV2;


/**
 * 服务提供者
 * @author we
 * @date 2021-05-10 17:43
 **/
public class ProviderService {
    public static void main(String[] args) {
        IHelloService helloService = new HelloServiceImpl();
        /**
         * 基于BIO的RPC实现
         */
         // RpcProxyServer proxyServer = new RpcProxyServer();
         // 发布服务
         // proxyServer.publisher(helloService,8888);

        /**
         * 基于NIO的RPC实现
         */
        RpcProxyServerV2 proxyServer = new RpcProxyServerV2(helloService,8080);
        new Thread(proxyServer).start();

    }
}
