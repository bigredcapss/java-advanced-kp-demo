package com.we.advanced.thread.deadlock;

/**
 * 通过第三方角色优化转账操作的线程类
 * @author we
 * @date 2021-05-24 08:39
 **/
public class TransferMoneyThread_DeathLockSolution01 implements Runnable {

    private Account fromAccount;
    private Account toAccount;
    private Integer amount;
    private Allocater allocater;

    public TransferMoneyThread_DeathLockSolution01(Account fromAccount, Account toAccount, Integer amount, Allocater allocater) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.allocater = allocater;
    }

    @Override
    public void run() {
        while (true){
            // 申请资源统一到一个临界区
            if(allocater.apply(fromAccount,toAccount)){
                try{
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
                }finally {
                    allocater.free(fromAccount,toAccount);
                }
            }
        }

    }
}
