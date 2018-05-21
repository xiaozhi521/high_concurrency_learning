package com.cn.third.SynchronouControl_1;

import java.util.concurrent.locks.LockSupport;

/**
 *  LockSupport 除了有定时阻塞功能外， 还支持中断影响。但是和其它接收中断的函数不一样，LockSupport.park()
 *  不会抛出 InterruptedException 异常。它只会默默的返回，但是我们可以从 Thread.interrupted() 等方法获得中断标记。
 *
 *
 */
public class LockSupportDemo {
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
                if(Thread.interrupted()){
                    System.out.println(getName() + "被中断了");
                }
            }
            System.out.println(getName() + "执行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.interrupt();
        LockSupport.unpark(t2);
    }
}
