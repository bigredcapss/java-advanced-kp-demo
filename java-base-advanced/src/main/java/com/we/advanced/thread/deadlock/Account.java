package com.we.advanced.thread.deadlock;

/**
 * 死锁demo
 * @author we
 * @date 2021-05-23 14:56
 **/
public class Account {
    private String accountName;
    private Integer balance;

    public Account(String accountName, Integer balance) {
        this.accountName = accountName;
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    /**
     * 取款操作
     * @param amount
     */
    public void decreDebit(Integer amount){
        this.balance-=amount;
    }

    /**
     * 存款操作
     * @param amount
     */
    public void increDebit(Integer amount){
        this.balance+=balance;
    }
}
