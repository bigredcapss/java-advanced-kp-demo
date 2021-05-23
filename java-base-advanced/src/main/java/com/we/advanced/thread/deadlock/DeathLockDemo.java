package com.we.advanced.thread.deadlock;

/**
 * @author we
 * @date 2021-05-23 15:04
 **/
public class DeathLockDemo {
    public static void main(String[] args) {
        Account fromAccount = new Account("张三",1000);
        Account toAccount = new Account("李四",2000);
        // 任务一：张三向李四转100元
        Thread threadA = new Thread(new TransferMoneyThread(fromAccount, toAccount, 100));
        // 任务二：李四向张三装200元
        //Thread threadB = new Thread(new TransferMoneyThread(toAccount, fromAccount, 200));
        threadA.start();
        //threadB.start();
    }
}
