package com.cn.second;

/**
 * 2.2.4 等待(wait)和通知(notify)
 *  Object.wait() 和 Thread.sleep() 方法都可以让线程等待若干时间，
 *  除了 wait() 可以被唤醒之外，另外一个主要的区别就是 wait() 方法会释放目标对象的锁，
 *  而 Thread.sleep() 方法不会释放任何资源
 */
public class Wait_Notify {
    final static Object object = new Object();
    public  static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis() + ": T1 start");

                try {
                    System.out.println(System.currentTimeMillis() + ": T1 wait");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ": T1 end");
            }
        }
    }
    public static class T2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis() + ": T2 start! notify one thread");
                object.notify();
                System.out.println(System.currentTimeMillis() + ": T2 end");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new T1();
        Thread thread2 = new T2();
        thread1.start();
        thread2.start();
    }

}
