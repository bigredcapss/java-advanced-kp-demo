package com.we.customer.rpcclient;

import com.we.customer.rpcclient.proxy.RpcProxyClient;
import com.we.customer.rpcserver.api.IHelloService;

/**
 * 服务调用者--消费服务
 * @author we
 * @date 2021-05-10 16:40
 **/
public class ConsumerService {
    public static void main(String[] args) {
        RpcProxyClient client = new RpcProxyClient();
        IHelloService helloService = client.clientProxy(IHelloService.class, "localhost", 8888);

        String content = helloService.sayHello("LiBai");
        System.out.println(content);
    }
}
