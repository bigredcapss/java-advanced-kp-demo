package com.we.advanced.net.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * NIO服务端--保证连接不阻塞
 * @author we
 * @date 2021-05-12 20:22
 **/
public class NIONBServerSocketChannelDemo {
    public static void main(String[] args) {


        try {
            // 创建一个ServerSocketChannel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 设置为非阻塞IO
            serverSocketChannel.configureBlocking(false);
            // 绑定一个端口（其中server.socket()是检索与此通道关联的服务器套接字）
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));

            // 不断监听客户端连接
            while(true){
                // 修改为非阻塞IO后，意味着这里不再阻塞了（也就是连接不阻塞了）
                SocketChannel socketChannel = serverSocketChannel.accept();
                // 如果socketChannel不为null,说明有连接进来
                if(socketChannel!=null){
                    // 读取客户端请求进byteBuffer
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    socketChannel.read(byteBuffer);
                    System.out.println(new String(byteBuffer.array()));

                    // 读完之后，再把消息写回到客户端
                    // 睡眠10s意味着客户端，在没有拿到消息之前一直处于阻塞状态
                    Thread.sleep(10000);
                    // 为从Buffer中取出数据做准备
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
                }else{
                    Thread.sleep(1000);
                    System.out.println("没有客户端连接进来");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
