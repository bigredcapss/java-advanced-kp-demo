package com.we.advanced.thread;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch模拟1000个线程并发场景
 * @author we
 * @date 2021-05-19 10:53
 **/
public class CountDownLatchDemo2 implements Runnable {

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(new CountDownLatchDemo2()).start();
        }
        // 唤醒线程
        countDownLatch.countDown();
    }

    @Override
    public void run() {
        try {
            System.out.println("我被阻塞了");
            countDownLatch.await();
            System.out.println("我被唤醒了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
