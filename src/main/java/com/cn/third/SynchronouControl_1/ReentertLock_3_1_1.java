package com.cn.third.SynchronouControl_1;

import java.util.concurrent.locks.ReentrantLock;
/**
 * 3.1.1  Synchronized 的功能扩展：重入锁
 *  重入锁可以完全代替 Synchronized 关键字。在JDK 5.0 的早期版本中，重入锁的性能远远好于 Synchronized，
 *  但是，从JDK6.0 开始， JDK 在Synchronized 上做了大量的优化，使得两者的性能差距并不大。
 *
 *  ReentrantLock 几个重要的方法整理如下：
 *      lock() : 获得锁，如果锁已经被占用，则等待
 *      lockInterruptibly() : 获得锁，优先响应中断
 *      tryLock() : 尝试获得锁，如果成功，返回true，失败返回false，该方法不等待，立即返回。
 *      tryLock(long time, TimeUnit unit) : 在给定时间内尝试获得锁
 *      unlock() : 释放锁
 *  就重入锁的实现来看，它主要集中在 java 层面。在重入锁视线中，主要包含三个要素：
 *      第一：是原子状态。原子状态使用CAS操作来存储当前锁的状态，判断锁是否已经被别的线程占有。
 *      第二：是等待队列。所有没有请求到锁的线程，会进入等待队列进行等待。待有线程释放锁后，系统就能从等待队列中
 *          唤醒一个线程，继续工作。
 *      第三：是阻塞原语park() 和 unpark() ，用来挂起和恢复线程。没有得到锁的线程将被挂起。
 *
 *      中断响应  ： IntLock.Class
 *      锁申请等待限时  ： TimeLock.Class
 *      锁申请等待限时  ： TryLock.Class
 *      公平锁  ： FairLock.Class
 */
public class ReentertLock_3_1_1 implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for( int j = 0; j < 1000000; j++){
            lock.lock();
            try{
                i++;
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentertLock_3_1_1 reentertLock = new ReentertLock_3_1_1();
        Thread t1 = new Thread(reentertLock);
        Thread t2 = new Thread(reentertLock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
