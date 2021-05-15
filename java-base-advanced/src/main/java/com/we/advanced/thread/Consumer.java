package com.we.advanced.thread;


import java.util.Queue;

/**
 * 消费者线程
 * @author we
 * @date 2021-05-15 17:28
 **/
public class Consumer implements Runnable {
    private Queue<String> bags;
    private int size;

    public Consumer(Queue<String> bags, int size) {
        this.bags = bags;
        this.size = size;
    }

    @Override
    public void run() {
        while (true){
            synchronized (bags){
                while (bags.isEmpty()){
                    System.out.println("bags已经空了");
                    // 阻塞
                    try {
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
                String bag = bags.remove();
                System.out.println("消费者-消费："+bag);
                // 唤醒生产者
                bags.notifyAll();
            }
        }

    }

}
