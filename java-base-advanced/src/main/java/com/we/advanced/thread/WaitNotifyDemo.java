package com.we.advanced.thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * wait/notify本质上其实是一种条件的竞争，至少来说，wait和notify方法一定是互斥存在的，既然要实现互斥，那么synchronized就是一个
 * 很好的解决办法；
 * wait/notify是基于synchronized来实现通信的，两者必须要在一个锁的范围内；
 *
 * @author we
 * @date 2021-05-15 17:29
 **/
public class WaitNotifyDemo {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        int size = 10;
        Producer producer = new Producer(queue, size);
        Consumer consumer = new Consumer(queue, size);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);

        t1.start();
        t2.start();

    }

}
