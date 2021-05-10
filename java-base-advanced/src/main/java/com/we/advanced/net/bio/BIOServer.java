package com.we.advanced.net.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO服务端进程
 * @author we
 * @date 2021-05-10 10:14
 **/
public class BIOServer {
    /**
     * 启动一个服务端
     * @param args
     */
    public static void main (String[] args)
    {
        final int DEFAULT_PORT = 8080;
        ServerSocket serverSocket = null;
        try
        {
            // 向OS注册一个服务，绑定监听的端口
            serverSocket = new ServerSocket(DEFAULT_PORT);
            while(true){
                // 阻塞操作(如果没有任何客户端去发起连接到当前客户端的话，这里处于阻塞状态)，等待客户端的连接
                // 这里的Socket相当于一个管道，之后的IO操作都要基于这个管道
                Socket socket = serverSocket.accept();
                // 代码执行到这里，说明已经有客户端连接成功了
                System.out.println("客户端："+socket.getPort()+"已连接");

                // 然后构建IO进行客户端的输入和基于服务端的回写
                // 因为我们基于的是TCP协议，TCP协议是一个双工协议，所谓双工协议就是可以双方相互通信的协议；

                // 构建一个高效的字符缓冲输入流，【这里当客户端没有传过来数据时，这里仍然是阻塞的】
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // 获取客户端输入的信息
                String clientStr = bufferedReader.readLine();
                System.out.println("收到客户端的请求信息："+clientStr);

                // 为了演示效果，处理每一个客户端请求时，睡眠15s
                Thread.sleep(15000);

                // 基于服务端的回写的字符缓冲输出流
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                // 这里加\n换行，否则客户端的readLine()会一直处于阻塞状态,会报出Connection reset异常信息
                bufferedWriter.write("我已经收到了消息\n");
                bufferedWriter.flush();
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
