package com.we.advanced.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * NIO客户端--IO仍然是阻塞的
 * @author we
 * @date 2021-05-12 20:15
 **/
public class NIOBSocketChannelDemo {
    public static void main(String[] args) {

        try {
            // 创建SocketChannel
            SocketChannel socketChannel = SocketChannel.open();
            // 请求连接指定主机和端口服务
            socketChannel.connect(new InetSocketAddress("localhost",8080));

            // 向缓冲区写数据
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put("Hello,I am SocketChannel Client01".getBytes());

            // 为取出数据做准备~limit变为position,position变为0
            byteBuffer.flip();

            // 把缓冲区数据写出去
            socketChannel.write(byteBuffer);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
