package com.we.customer.rpcserver.provider.handler;

import com.we.customer.rpcserver.api.RpcRequest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 基于NIO--优化ProcessorHandler
 * @author we
 * @date 2021-05-10 19:22
 **/
public class ProcessorHandlerV2 {
    SocketChannel socket;
    Object service;

    public ProcessorHandlerV2(SocketChannel socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    public void doProcessor(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        try {
            socket.read(byteBuffer);
            int position = byteBuffer.position();
            String message = new String(byteBuffer.array(),0, position);
            String[] requestData = message.split("@");
            String className = requestData[0];
            // Netty ->
            /**
             * 对象的序列化
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object inovke(RpcRequest rpcRequest) throws Exception {
        Class clazz=Class.forName(rpcRequest.getClassName());
        Method method=clazz.getMethod(rpcRequest.getMethodName(),rpcRequest.getTypes());
        Object result=method.invoke(service,rpcRequest.getParameters());
        return result;
    }


}
