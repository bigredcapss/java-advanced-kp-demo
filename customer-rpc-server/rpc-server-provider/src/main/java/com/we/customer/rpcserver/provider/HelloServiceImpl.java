package com.we.customer.rpcserver.provider;

import com.we.customer.rpcserver.api.IHelloService;

/**
 * 实现API接口
 * @author we
 * @date 2021-05-10 16:23
 **/
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String content) {
        return "Hello Content:"+content;
    }
}
