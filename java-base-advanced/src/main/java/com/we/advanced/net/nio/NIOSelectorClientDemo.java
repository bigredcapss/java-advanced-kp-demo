package com.we.advanced.net.nio;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 基于Selector实现的客户端
 * @author we
 * @date 2021-05-15 11:00
 **/
public class NIOSelectorClientDemo {
    static Selector selector;

    public static void main(String[] args) {

        try {
            selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost",8080));

            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            while (true){
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if(selectionKey.isConnectable()){
                        handlerConnect(selectionKey);
                    }else if(selectionKey.isReadable()){
                        handlerRead(selectionKey);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立连接后，向服务端写数据
     * @param selectionKey
     */
    private static void handlerConnect(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        // 这时连接已经建立了，稳妥期间，确认连接建立
        if(socketChannel.isConnectionPending()){
            socketChannel.finishConnect();
        }
        socketChannel.configureBlocking(false);
        socketChannel.write(ByteBuffer.wrap("Hello Server,I am NIO client".getBytes()));
        // 向服务端写数据后，读取服务端返回的数据，触发handlerRead
        socketChannel.register(selector,SelectionKey.OP_READ);

    }

    /**
     * 读取客户端返回的数据
     * @param selectionKey
     * @throws IOException
     */
    private static void handlerRead(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        socketChannel.read(byteBuffer);
        socketChannel.read(byteBuffer);
        System.out.println("client receive msg:"+new String(byteBuffer.array()));
    }
}
