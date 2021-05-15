package com.we.advanced.thread;

import java.util.concurrent.TimeUnit;

/**
 * Thread.interrupted()对设置中断标识的线程复位，并且返回当前的中断状态。
 * @author we
 * @date 2021-05-15 17:50
 **/
public class InterruptedDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            while(true){
                // true表示被中断过
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("before:"+Thread.currentThread().isInterrupted());
                    Thread.interrupted(); // 对中断标识复位 false
                    System.out.println("after:"+Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); // 中断
    }
}
