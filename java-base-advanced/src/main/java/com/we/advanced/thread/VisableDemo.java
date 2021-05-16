package com.we.advanced.thread;

/**
 * 线程安全的本质--可见性
 * @author we
 * @date 2021-05-16 10:57
 **/
public class VisableDemo {

    /**
     * volatile可以解决可见性，有序性问题
     */
    public static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
           int i = 0;
           // 在主线程中修改stop的值对于线程thread是不可见的
           while (!stop){
               i++;
           }
           System.out.println("result:"+i);
        });
        thread.start();
        System.out.println("begin start thread");
        Thread.sleep(1000);
        // 主线程中修改stop的值
        stop=true;

    }
}
