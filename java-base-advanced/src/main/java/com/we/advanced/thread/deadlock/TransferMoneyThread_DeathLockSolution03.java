package com.we.advanced.thread.deadlock;

/**
 * 破环循环等待条件：按照线程申请资源的顺序来进行资源的预占用；
 * 通过对线程进行排序，通过HashCode排序优化转账操作的线程类
 * @author we
 * @date 2021-05-24 20:42
 **/
public class TransferMoneyThread_DeathLockSolution03 implements Runnable{
    private Account fromAccount;//转出账户
    private Account toAccount;//转入账户
    private Integer amount;

    public TransferMoneyThread_DeathLockSolution03(Account fromAccount, Account toAccount, Integer amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        Account left = null;
        Account right = null;

        if(fromAccount.hashCode()>toAccount.hashCode()){
            left = toAccount;
            right = fromAccount;
        }
        while(true){
            synchronized (fromAccount){
                synchronized (toAccount){
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
