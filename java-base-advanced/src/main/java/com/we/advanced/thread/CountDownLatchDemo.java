package com.we.advanced.thread;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch是一个同步工具类，它允许一个或多个线程一直等待，直到其它线程的操作执行完毕再执行；
 * @author we
 * @date 2021-05-19 10:44
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(()->{
            // 倒计时3-1
           countDownLatch.countDown();
        }).start();

        new Thread(()->{
            // 倒计时2-1
            countDownLatch.countDown();
        }).start();

        new Thread(()->{
            // 倒计时1-1=0触发主线程的唤醒操作
            countDownLatch.countDown();
        }).start();
        // 阻塞主线程
        countDownLatch.await();
        System.out.println("线程执行完毕");

    }
}
