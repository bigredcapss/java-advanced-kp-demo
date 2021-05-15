package com.we.advanced.thread;

import java.util.concurrent.*;

/**
 * 使用Callable与Future接口创建线程
 * @author we
 * @date 2021-05-15 14:26
 **/
public class CallableThreadDemo implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("当前线程："+Thread.currentThread().getName());
        Thread.sleep(1000);
        return "Hello,Thread";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(new CallableThreadDemo());
        System.out.println(Thread.currentThread().getName()+"---"+future.get());
    }
}
