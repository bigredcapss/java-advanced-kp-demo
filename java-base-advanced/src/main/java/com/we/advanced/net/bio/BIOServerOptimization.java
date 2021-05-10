package com.we.advanced.net.bio;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO服务端优化
 * @author we
 * @date 2021-05-10 14:14
 **/
public class BIOServerOptimization {
    public static void main(String[] args) {
        final int DEFAULT_PORT = 8080;
        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(DEFAULT_PORT);

            // 线程池优化服务端处理能力
            ExecutorService executorService = Executors.newFixedThreadPool(5);

            while(true){
                // 阻塞1：连接阻塞
                Socket socket = serverSocket.accept();
                BIOServerThread bioServerThread = new BIOServerThread(socket);
                // 提交给线程池执行
                executorService.submit(bioServerThread);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
