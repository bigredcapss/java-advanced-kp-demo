package com.we.advanced.thread;

/**
 * Thread.join()保证线程执行结果的可见性
 * @author we
 * @date 2021-05-15 16:43
 **/
public class ThreadJoinDemo {
    private static int i = 0;
    private static int x = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            // 阻塞操作
            i=1;
            x=2;
        });
        Thread t2 = new Thread(()->{
            i=x+2;
        });
        // 两个线程顺序执行
        t1.start();
        // t1线程的执行结果对于t2可见（t1线程一定要比t2线程先执行完毕）--阻塞
        t1.join();
        t2.start();
        Thread.sleep(1000);
        System.out.println("result:"+i);

    }
}
