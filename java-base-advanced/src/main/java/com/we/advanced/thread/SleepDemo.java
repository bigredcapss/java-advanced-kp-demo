package com.we.advanced.thread;

/**
 * 使线程暂停一段时间，直到等待的时间结束才恢复执行或在这段时间内被中断
 * @author we
 * @date 2021-05-15 16:59
 **/
public class SleepDemo extends Thread{

    @Override
    public void run() {
        System.out.println("begin:"+System.currentTimeMillis());

        try {
            // 睡眠3s
            Thread.sleep(3000);
            System.out.println("end:"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SleepDemo().start();
    }
}
