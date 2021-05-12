package com.we.advanced.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * NIO服务端--连接仍然是阻塞的
 * @author we
 * @date 2021-05-12 19:57
 **/
public class NIOBServerSockerChannelDemo {
    public static void main(String[] args) {
        try {
            // 创建一个ServerSocketChannel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 绑定一个端口（其中server.socket()的作用是检索与此通道关联的服务器套接字）
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));

            // 不断监听客户端连接
            while (true){
                // 这里仍然是阻塞的
                SocketChannel socketChannel = serverSocketChannel.accept();
                // 如果代码进入这里，说明有客户端连接进来
                // 读取客户端请求进byteBuffer
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                socketChannel.read(byteBuffer);
                System.out.println(new String(byteBuffer.array()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
