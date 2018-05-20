package com.cn.third.SynchronouControl_1;

import java.util.concurrent.locks.ReentrantLock;

/**
 *  锁申请等待限时
 *
 *  除了等待外部通知之外，要避免死锁还有另外一种方法，那就是限时等待。
 *
 *  tryLock() 方法进行一次限时等待
 */
public class TryLock implements Runnable{
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public TryLock(int lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        if(lock == 1){
            while (true){
                if(lock1.tryLock()){
                    try{
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(lock2.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getId() + ": My job done");
                                return;
                            }finally {
                                lock2.unlock();
                            }
                        }
                    }finally {
                        lock1.unlock();
                    }
                }
            }
        }else {
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + ": My job done");
                                return;
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } finally {
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TryLock timeLock1 = new TryLock(1);
        TryLock timeLock2 = new TryLock(2);
        Thread t1 = new Thread(timeLock1);
        Thread t2 = new Thread(timeLock2);
        t1.start();
        t2.start();
    }
}
