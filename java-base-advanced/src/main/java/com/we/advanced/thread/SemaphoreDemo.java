package com.we.advanced.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore可以控制同时访问的线程个数，通过acquire获取一个许可，如果没有就等待，通过release释放一个许可；
 * @author we
 * @date 2021-05-19 11:15
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 当前可以获得的最大许可数量是5个
        Semaphore semaphoreDemo = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new Car(i,semaphoreDemo).start();
        }
    }

    static class Car extends Thread{
        private int num;
        private Semaphore semaphore;

        public Car(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // 获得许可，若获取不到许可，就会被阻塞
                semaphore.acquire();
                System.out.println("第"+num+"辆车占用了一个停车位");
                TimeUnit.SECONDS.sleep(100);
                System.out.println("第"+num+"辆车走了");
                // 释放许可
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
