package com.we.advanced.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁示例
 * 重入锁：一个持有锁的线程，在释放锁之前，如果再次访问加了该同步锁的其它方法，这个线程不需要再次争抢锁，只需要记录重入次数。
 * @author we
 * @date 2021-05-18 19:48
 **/
public class LockDemo {
    static Lock lock = new ReentrantLock();
    public static int count = 0;

    /**
     * 递增
     */
    public static void incr(){
        try{
            // 加锁
            lock.lock();
            Thread.sleep(1);
            // 访问加该同步锁的decr方法
            decr();
            // count++只会由一个线程来执行
            count++;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 释放锁
            lock.unlock();
        }
    }

    /**
     * 递减
     */
    public static void decr(){
        lock.lock();
        count--;
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(LockDemo::incr).start();
        }
        Thread.sleep(4000);
        System.out.println("result:"+count);
    }
}
