package com.we.advanced.thread;

/**
 * 继承Thread类来创建线程
 * @author we
 * @date 2021-05-15 14:29
 **/
public class FirstThreadDemo extends Thread {

    private int i;

    /**
     * 线程执行体
     */
    @Override
    public void run() {
        for (i = 0; i < 100; i++) {
            System.out.println(this.getName()+" "+i);
        }
    }

    /**
     * 主线程
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i);
            if(i==20){
                // 创建并启动第一个新线程
                new FirstThreadDemo().start();
                // 创建并启动第二个线程
                new FirstThreadDemo().start();
            }
        }
    }
}
