package com.we.advanced.thread;

/**
 * ThreadLocal示例
 * @author we
 * @date 2021-05-16 16:38
 **/
public class ThreadLocalDemo {
    private static Integer num = 0;

    public static final ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 0);

    //public static final ThreadLocal<Integer> local1 = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        // 希望每个线程拿到的都是0
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(()->{
                //num+=5;
                // 获取初始值
                Integer num = local.get();
                //local1.get();
                num+=5;
                local.set(num);
                System.out.println(Thread.currentThread().getName()+"->"+num);
            },"Thread-"+i);
        }
        for(Thread thread:threads){
            thread.start();
        }
    }

}
