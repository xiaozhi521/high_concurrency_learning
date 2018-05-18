package com.cn.second;

/**
 * 2.2.5 挂起(suspend) 和 继续执行(resume)线程
 */
public class Suspend_Resume_Bad_2_2_5 {
    public static Object u= new Object();
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
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000);
        t2.start();
        t2.resume();
        t1.resume();
    }


}
