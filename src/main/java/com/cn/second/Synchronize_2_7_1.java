package com.cn.second;

/**
 * 2.7 线程安全的概念与 synchronized
 * 并行程序开发的一大关注点就是线程安全。一般来说，程序并行化是为了获得更高的执行效率，但前提是，高效率不能以牺牲
 * 正确性为代价。如果程序并行化后，连基本的执行结果的正确性都无法保证，那么并行程序本身也就没有任何意义了。
 * 因此，线程安全就是并行程序的基本和根基。
 * volatile 关键字并不能真正的保证线程安全。他只能确保一个线程修改了数据后，其他线程能够看到这个改动。但两个线程
 * 同时修改某一个数据时，却依然会发生冲突。
 *
 * 关键字 synchronized 的作用是实现线程间的同步。他的工作是对同步的代码加锁，使得每一次，只能有一个线程进入同步块，
 * 从而保证线程的安全性
 *
 * synchronized 的用法：
 *  指定加锁对象：对给定对象加锁，进入同步代码前要获得给定对象的锁
 *  直接作用于实例方法：相当于对当前实例加锁，进入同步代码前要获得当前实例的锁
 *  直接作用于静态方法：相当于对当前类加锁，进入同步代码前要获得当前类的锁
 *
 *  指定加锁对象
 */
public class Synchronize_2_7_1 implements Runnable{
    static Synchronize_2_7_1 instance = new Synchronize_2_7_1();
    static volatile int i = 0;
    public  void increse(){
        i++;
    }
    @Override
    public void run() {
        for(int j = 0;j < 100000; j++){
            synchronized (instance){
                increse();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
