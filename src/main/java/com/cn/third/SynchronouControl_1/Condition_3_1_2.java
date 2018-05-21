package com.cn.third.SynchronouControl_1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  3.1.2 重入锁的好搭档 ： Condition 条件
 *
 *
 *  void await() throws InterruptedException
 *      造成当前线程在接到信号或被中断之前一直处于等待状态。
 *      会使当前线程等待，同时释放当前锁，当前其他线程中使用 signal() 或者 signlAll() 方法时，线程会重
 *      新获得锁并继续执行。或者当线程被中断时，也能跳出等待。这和 Object.wait() 方法很相似。
 *  void awaitUninterruptibly()
 *      与 await() 方法基本相同，但是它不会在等待过程中响应中断
 *  long awaitNanos(long nanosTimeout) throws InterruptedException
 *
 *  boolean await(long time,TimeUnit unit) throws InterruptedException
 *
 *  boolean awaitUntil(Date deadline) throws InterruptedException
 *      造成当前线程在接到信号、被中断或到达指定最后期限之前一直处于等待状态。
 *  void signal()
 *      方法用户唤醒一个在等待中的线程。
 *      如果所有的线程都在等待此条件，则选择其中的一个唤醒。在从 await 返回之前，该线程必须重新获取锁。
 *  void signalAll()
 *      唤醒所有等待线程。
 *      如果所有的线程都在等待此条件，则唤醒所有线程。在从 await 返回之前，每个线程都必须重新获取锁。
 */
public class Condition_3_1_2 implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Condition_3_1_2  condition_3_1_2 = new Condition_3_1_2();
        Thread t1 = new Thread(condition_3_1_2);
        t1.start();
        Thread.sleep(2000);
        //通知线程 t1 继续执行、
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
