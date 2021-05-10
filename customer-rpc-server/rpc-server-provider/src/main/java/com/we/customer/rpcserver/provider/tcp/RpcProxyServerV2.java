package com.we.customer.rpcserver.provider.tcp;

import com.we.customer.rpcserver.provider.handler.ProcessorHandler;
import com.we.customer.rpcserver.provider.handler.ProcessorHandlerV2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 优化RpcProxyServer
 * @author we
 * @date 2021-05-10 19:21
 **/
public class RpcProxyServerV2 implements Runnable {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean stop;
    private Object service;

    public RpcProxyServerV2(Object service,int port) {
        this.service = service;
        try {
            selector=Selector.open();
            serverSocketChannel=ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(!stop){
            try {
                // 阻塞
                selector.select();
                Set<SelectionKey> selectionKeys=selector.selectedKeys();
                Iterator<SelectionKey> iterator=selectionKeys.iterator();
                while(iterator.hasNext()){
                    SelectionKey key=iterator.next();
                    iterator.remove();
                    try {
                        handleRequest(key);
                    }catch (Exception e){
                        key.cancel();
                        key.channel().close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleRequest(SelectionKey key) throws IOException {
        if(key.isValid()){
            if(key.isAcceptable()){
                ServerSocketChannel serverSocketChannel=(ServerSocketChannel)key.channel();
                SocketChannel socketChannel=serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector,SelectionKey.OP_READ);
            }else if(key.isReadable()){
                SocketChannel socketChannel=(SocketChannel)key.channel();
                new ProcessorHandlerV2(socketChannel,service).doProcessor();
            }
        }
    }
}
