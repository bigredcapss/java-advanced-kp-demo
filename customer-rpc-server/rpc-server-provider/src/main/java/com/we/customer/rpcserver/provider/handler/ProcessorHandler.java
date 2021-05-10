package com.we.customer.rpcserver.provider.handler;

import com.we.customer.rpcserver.api.RpcRequest;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 基于BIO+线程池--实现网络请求处理
 * @author we
 * @date 2021-05-10 17:48
 **/
public class ProcessorHandler implements Runnable{

    Socket socket;
    Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            // 反序列化请求消息
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
            Object result = invoke(rpcRequest);
            // 响应客户端请求
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流

        }

    }

    private Object invoke(RpcRequest rpcRequest) throws Exception {
        Class clazz = Class.forName(rpcRequest.getClassName());
        Method method = clazz.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
        Object result = method.invoke(service, rpcRequest.getParameters());
        return result;

    }
}
