package com.we.advanced.thread.deadlock;

/**
 * 转帐操作线程类
 * @author we
 * @date 2021-05-23 15:00
 **/
public class TransferMoneyThread implements Runnable{
    // 转出账户
    private Account fromAccount;
    // 转入账户
    private Account toAccount;
    // 转账金额
    private Integer amount;

    public TransferMoneyThread(Account fromAccount, Account toAccount, Integer amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        while (true){
            synchronized (fromAccount){
                synchronized (toAccount){
                    if(fromAccount.getBalance()>=amount){
                        fromAccount.decreDebit(amount);
                        toAccount.increDebit(amount);
                    }
                }
            }
            System.out.println(fromAccount.getAccountName()+"---------"+fromAccount.getBalance());
            System.out.println(toAccount.getAccountName()+"---------"+toAccount.getBalance());
        }
    }
}
