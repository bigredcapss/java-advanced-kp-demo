package com.we.advanced.thread;

/**
 * 问题代码--如何使用ThreadLocal解决
 * @author we
 * @date 2021-05-16 16:32
 **/
public class ThreadLocalProblemDemo {
    private static Integer num = 0;

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        // 希望每个线程拿到的都是0
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(()->{
                num+=5;
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
