package com.we.advanced.thread.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 破坏不可抢占条件：占用部分资源的线程可以进一步的申请资源，如果申请不到，可以主动释放其它占有的资源；
 * synchronized关键字：在申请不到资源时，就会阻塞；所以在synchronized层面解决不了该问题，但是J.U.C提供了Lock，可以破坏不可抢占条件，规避该问题；
 * 利用J.U.C中的Lock来规避死锁，若已经获得共享资源的线程，再次获得共享资源时，获取失败，则释放原先占有的资源，来破坏不可抢占
 * 条件，从而达到规避死锁的母的。
 * @author we
 * @date 2021-05-24 20:30
 **/
public class TransferMoneyThread_DeathLockSolution02 implements Runnable {
    private Account fromAccount;//转出账户
    private Account toAccount;//转入账户
    private Integer amount;
    Lock fromLock = new ReentrantLock();
    Lock toLock = new ReentrantLock();

    public TransferMoneyThread_DeathLockSolution02(Account fromAccount, Account toAccount, Integer amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        /**
         * tryLock()尝试获得锁，若获得锁成功，返回true;失败，返回false;
         */
        while (true){
            if (fromLock.tryLock()){
                if (toLock.tryLock()){
                    if(fromAccount.getBalance()>=amount){
                        fromAccount.decreDebit(amount);
                        toAccount.increDebit(amount);
                    }
                }
            }
            System.out.println(fromAccount.getAccountName()+"----"+fromAccount.getBalance());
            System.out.println(toAccount.getAccountName()+"----"+toAccount.getBalance());
        }

    }
}
