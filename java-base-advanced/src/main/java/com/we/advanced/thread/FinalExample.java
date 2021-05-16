package com.we.advanced.thread;

/**
 * final关键字--内存屏障
 * @author we
 * @date 2021-05-16 15:17
 **/
public class FinalExample {
    int i;// 普通变量
    final int j;// final变量
    static FinalExample obj;
    public FinalExample(){// 构造函数
        i = 1;// 写普通域
        j = 2;// 写final域
    }

    // 写线程A执行
    public static void writer(){
        obj = new FinalExample();
    }

    // 读线程B执行
    public static void reader(){
        FinalExample object = obj;// 读对象引用
        int a = object.i;// 读普通域
        int b = object.j;// 读final域
    }






}
