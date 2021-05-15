package com.we.advanced.thread;

import java.util.Queue;

/**
 * 生产者线程
 * @author we
 * @date 2021-05-15 17:23
 **/
public class Producer implements Runnable{
    private Queue<String> bags;
    private int size;

    public Producer(Queue<String> bags, int size) {
        this.bags = bags;
        this.size = size;
    }

    @Override
    public void run() {
        int i = 0;
        while(true){
            i++;
            synchronized (bags){
                while (bags.size()==size){
                    System.out.println("包已经满了");
                    try {
                        // 阻塞
                        bags.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("生产者-生产：bag"+i);
                bags.add("bag"+i);
                // 唤醒处于阻塞状态下的消费者
                bags.notifyAll();
            }
        }
    }
}
