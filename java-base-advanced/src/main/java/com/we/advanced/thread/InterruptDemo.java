package com.we.advanced.thread;

/**
 * 当其它线程通过调用当前线程的interrupt方法，表示向当前线程打个招呼，
 * 告诉他可以中断线程的执行了，至于什么时间中断，取决于当前线程自己；
 * @author we
 * @date 2021-05-15 17:43
 **/
public class InterruptDemo {
    private static int i = 0;

    public static void main(String[] args) {

        Thread thread = new Thread(()->{
            // Thread.currentThread().isInterrupted() 默认是false
            while(Thread.currentThread().isInterrupted()){
                // 正常的任务处理..
                i++;
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                // 抛出异常来响应客户端的中断请求
                e.printStackTrace();
            }
        });
        thread.start();
        // 调用stop()强制终止线程（不友好）
        thread.stop();
        //thread.interrupt();// 友好的中断线程方式

    }

}
