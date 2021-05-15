package com.we.advanced.thread;

import java.util.concurrent.TimeUnit;

/**
 * stop是在JVM层面强制终止线程执行，如果线程正在执行任务，调用stop方法会导致线程立即死亡，会出现一些不可预见的错误
 * @author we
 * @date 2021-05-15 17:36
 **/
public class StopDemo {

    static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new StopThread());
        t1.start();

        TimeUnit.SECONDS.sleep(2);
        stop=true;
        t1.stop();

    }

    static class StopThread implements Runnable{
        @Override
        public void run() {
            while(!stop){
                System.out.println("持续运行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
