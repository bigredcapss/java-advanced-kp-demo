package com.we.advanced.thread;

/**
 * 实现Runnable接口创建并启动线程
 * @author we
 * @date 2021-05-15 14:34
 **/
public class RunnableThreadDemo implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i);
            if(i==20){
                RunnableThreadDemo runnableThreadDemo = new RunnableThreadDemo();
                new Thread(runnableThreadDemo,"runnable1").start();
                new Thread(runnableThreadDemo,"runnable2").start();
            }
        }
    }
}
