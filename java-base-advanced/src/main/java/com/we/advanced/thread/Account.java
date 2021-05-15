package com.we.advanced.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock对象来控制同步，并使用Condition对象来控制线程的协调运行
 * @author we
 * @date 2021-05-15 15:49
 **/
public class Account {

    // 显示定义Lock对象
    private final Lock lock = new ReentrantLock();
    // 获得指定Lock对象的Condition
    private final Condition condition = lock.newCondition();

    private String accountNo;
    private double balance;

    // 标识账户中是否已有存款的标志
    private boolean flag = false;

    public Account(){}

    public Account(String accountNo,double balance){
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return this.balance;
    }

    /**
     * 取钱操作
     * @param drawAmount
     */
    public void draw(double drawAmount){
        // 加锁
        lock.lock();
        try {
            //如果flag为假，表明账户中还没有人存钱进去，取钱方法阻塞
            if(!flag){
                condition.await();
            }else {
                //执行取钱操作
                System.out.println(Thread.currentThread().getName()+" 取钱："+drawAmount);
                balance -=drawAmount;

                System.out.println("账户余额为："+balance);
                //将标识账户是否已有存款的标识设置为false
                flag = false;
                //唤醒其它线程
                condition.signalAll();


            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            //释放锁
            lock.unlock();
        }
    }

    /**
     * 存钱操作
     * @param depositAmount
     */
    public void deposit(double depositAmount){
        lock.lock();
        try {
            //如果flag为真，表明账户有人存钱进去，存钱方法阻塞
            if(flag){
                condition.await();
            }else{
                //执行存款操作
                System.out.println(Thread.currentThread().getName()+"存款："+depositAmount);
                balance+=depositAmount;

                System.out.println("账户余额为："+balance);
                // 将标识账户是否已有存款的标识设置为true
                flag = true;
                // 唤醒其它线程
                condition.signalAll();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }



}
