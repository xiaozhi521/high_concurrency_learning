package com.cn.third.SynchronouControl_1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  公平锁
 */
public class FairLock implements Runnable{
    public static ReentrantLock reentrantLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true){
            try {
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            }finally {
                reentrantLock.unlock();
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        FairLock timeLock = new FairLock();
        Thread t1 = new Thread(timeLock,"FairLock1");
        Thread t2 = new Thread(timeLock,"FairLock2");
        t1.start();
        t2.start();
    }
}
