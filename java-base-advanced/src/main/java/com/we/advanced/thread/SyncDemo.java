package com.we.advanced.thread;

/**
 * 锁的范围--对象级别的锁
 * @author we
 * @date 2021-05-16 11:44
 **/
public class SyncDemo {
    // 对象锁(同一个对象有效)
    public synchronized void demo1(){}
    // 对象锁
    public void demo2(){
        //TODO
        synchronized (this){
        }
        //TODO
    }

    public static void main(String[] args) {
        SyncDemo syncDemo1=new SyncDemo();
        SyncDemo syncDemo2=new SyncDemo();
        // 无法实现两个线程的互斥
        new Thread(()->{  // 这里的锁拿到的是syncDemo1这个实例
            syncDemo1.demo1();
        }).start();

        new Thread(()->{
            syncDemo2.demo1();// 这里的锁拿到的是syncDemo2这个实例
        }).start();

        // 可以实现两个线程的互斥
        new Thread(()->{  // syncDemo1这个实例
            syncDemo1.demo1();
        }).start();

        new Thread(()->{
            syncDemo1.demo1();// syncDemo1这个实例
        }).start();
    }
}
