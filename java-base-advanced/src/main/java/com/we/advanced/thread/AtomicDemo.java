package com.we.advanced.thread;

/**
 * 线程安全的本质--原子性Demo
 * @author we
 * @date 2021-05-16 10:41
 **/
public class AtomicDemo {

    public static int count=0;
    public static void incr(){
        try {
            // 可能发生线程切换
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         * 可以在终端通过javap -v AtomicDemo.class查看
         * 汇编指令：
         * getstatic
         * iadd
         * putstatic
         */
        count++; //count++并不是一个原子操作
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(AtomicDemo::incr).start();
        }
        // 这里由于线程之间是异步的,而子线程也不阻塞，如果再不让主线程阻塞一会,而是直接结束,
        // 那么主线程是不会等到子线程执行完毕的,可能会造成不必要的错误。
        Thread.sleep(4000);
        System.out.println("result:"+count);

    }



}
