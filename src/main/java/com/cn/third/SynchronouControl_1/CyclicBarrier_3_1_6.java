package com.cn.third.SynchronouControl_1;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *  3.1.6 循环栅栏： CyclicBarrier
 *  CyclicBarrier 是另外一种多线程并发控制实用工具。和CountDownLatch非常类似，它也可以实现线程间的计数等待，但他的
 *  功能比 CountDownLatch 更加复杂和强大。
 *
 *  CyclicBarrier 用来阻止线程继续执行，要求线程在栅栏处等待。
 */
public class CyclicBarrier_3_1_6 {
    public static class Soldier implements Runnable{
        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        public Soldier(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                //等待所有的士兵到齐
                cyclicBarrier.await();
                doWork();
                //等待所有的士兵完成工作
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
        void doWork(){
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + ":完成任务");
        }
    }
   public static class BarrierRun implements Runnable{
        boolean flag;
        int N;

       public BarrierRun(boolean flag, int n) {
           this.flag = flag;
           N = n;
       }

       @Override
       public void run() {
           if(flag){
               System.out.println("司令：[士兵" + N + "个，任务完成！]");
           }else{
               System.out.println("司令：[士兵" + N + "个，集合完毕！]");
               flag = true;
           }
       }
   }

    public static void main(String[] args) {
        final  int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N,new BarrierRun(flag,N));
        //设置屏障点，主要是为了执行这个方法
        System.out.println("集合队伍");
        for(int i = 0; i < N; ++i){
            System.out.println("士兵" + i + "报道");
            allSoldier[i] = new Thread(new Soldier("士兵"+i,cyclic));
            allSoldier[i].start();
        }
    }
}
