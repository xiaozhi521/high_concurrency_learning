package com.cn.third.SynchronouControl_1;


import java.util.concurrent.locks.LockSupport;

/**
 *  线程阻塞工具类 ； LockSupport
 *
 *  LockSupport 的静态方法park()可以阻塞当前线程，类似的还有parkNanos(),parkUntil()等方法，他们实现了一个限时等待
 */
public class LockSupport_3_1_7 {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");
    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name){
            super.setName(name);
        }
        @Override
        public void run() {
            synchronized (u){
                System.out.println("in " + getName());
                LockSupport.park();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000);
        t2.start();
       LockSupport.unpark(t1);
       LockSupport.unpark(t2);
       t1.join();
       t2.join();
    }
}
