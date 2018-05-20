package com.cn.third.SynchronouControl_1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  锁申请等待限时
 *
 *  除了等待外部通知之外，要避免死锁还有另外一种方法，那就是限时等待。
 *
 *  tryLock() 方法进行一次限时等待
 */
public class TimeLock implements Runnable{
    public static ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        try {
            //限时等待，超过5秒没有得到锁，会返回false
            if(reentrantLock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(6000);
            }else{
                System.out.println("get lock faild");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(reentrantLock.isHeldByCurrentThread()){
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);
        t1.start();
        t2.start();
    }
}
