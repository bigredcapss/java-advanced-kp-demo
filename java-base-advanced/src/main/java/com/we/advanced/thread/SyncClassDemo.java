package com.we.advanced.thread;

/**
 * 锁的范围--类锁
 * @author we
 * @date 2021-05-16 11:42
 **/
public class SyncClassDemo {
    // 类级别的锁  SyncClassDemo.class
    public synchronized static void demo3(){

    }
    // 类级别的锁
    public void demo4(){
        synchronized (SyncClassDemo.class){

        }

    }

    public static void main(String[] args) {
        SyncClassDemo syncDemo1=new SyncClassDemo();
        SyncClassDemo syncDemo2=new SyncClassDemo();
        // 可以实现两个线程的互斥.
        new Thread(()->{  // syncDemo.class
            syncDemo1.demo4();
        }).start();

        new Thread(()->{ // BLOCKED状态
            syncDemo2.demo4();// syncDemo.class
        }).start();
    }
}
