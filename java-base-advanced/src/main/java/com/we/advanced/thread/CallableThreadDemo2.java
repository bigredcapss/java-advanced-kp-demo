package com.we.advanced.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author we
 * @date 2021-05-15 14:42
 **/
public class CallableThreadDemo2 {
    public static void main(String[] args) {
        // 创建Callable对象
        CallableThreadDemo2 callableThreadDemo2 = new CallableThreadDemo2();
        // 先使用Lambda表达式创建Callable<Integer>对象,使用Future来包装Callable对象
        FutureTask<Integer> task = new FutureTask<>((Callable<Integer>) () -> {
            int i = 0;
            for (i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " 的循环变量i的值：" + i);
            }
            return i;
        });

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+ " 的循环变量i的值："+i);
            if(i==20){
                // 实质还是以Callable对象来创建并启动线程的
                new Thread(task,"callable").start();
            }
        }

        try {
            // 获取线程返回值
            System.out.println("子线程的返回值："+task.get());
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
