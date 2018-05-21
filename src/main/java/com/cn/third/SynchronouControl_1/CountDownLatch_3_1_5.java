package com.cn.third.SynchronouControl_1;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  3.1.5 倒计时锁存器 CountDownLatch
 *
 *  CountDownLatch 是一个非常实用的多线程控制工具类。
 *  这个工具通常用来控制线程等待，它可以让某一个线程等待直到倒计时结束，在开始执行。
 *
 *  public CountDownLatch(int count) 构造一个用给定计数初始化的 CountDownLatch。
 *
 *  void countDown()
 *      递减锁存器的计数，如果计数到达零，则释放所有等待的线程。
 *      如果当前计数大于零，则将计数减少。如果新的计数为零，出于线程调度目的，将重新启用所有的等待线程。
 *      如果当前计数等于零，则不发生任何操作。
 *  public void await() throws InterruptedException
 *      使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断。
 *      如果当前计数为零，则此方法立即返回。
 *      如果当前计数大于零，则出于线程调度目的，将禁用当前线程，且在发生以下两种情况之一前，该线程将一直处于休眠状态：
 */
public class CountDownLatch_3_1_5 implements Runnable{
    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatch_3_1_5 demo = new CountDownLatch_3_1_5();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10;i++){
            executorService.submit(demo);
        }
        //等待检查
        end.await();
        //发射火箭
        System.out.println("Fire!");
        executorService.shutdown();
    }
}
