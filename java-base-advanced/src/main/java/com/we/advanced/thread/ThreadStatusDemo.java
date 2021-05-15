package com.we.advanced.thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程的生命周期Demo
 * @author we
 * @date 2021-05-15 14:59
 **/
public class ThreadStatusDemo {
    public static void main(String[] args) {

        // TIME_WAITING
        new Thread(()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"Time_Waiting_Demo").start();

        // WAITING
        new Thread(()->{
            while (true){
                synchronized (ThreadStatusDemo.class){
                    try {
                        ThreadStatusDemo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Waiting_Demo").start();

        new Thread(new BlockDemo(),"Blocked-Demo-01").start();
        new Thread(new BlockDemo(),"Blocked-Demo-02").start();



    }

    static class BlockDemo extends Thread{
        @Override
        public void run() {
            synchronized (BlockDemo.class){
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
