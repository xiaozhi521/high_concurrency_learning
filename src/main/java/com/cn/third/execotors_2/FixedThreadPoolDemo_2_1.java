package com.cn.third.execotors_2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  1、固定大小的线程池
 *  newFixedThreadPool()方法：该方法返回一个固定线程数量的线程池。该线程池中的线程数量时钟不变。当有一个新的任务
 *  提交时，线程池中若有空闲线程，则立即执行。若没有，则新的任务会被暂存在一个任务队列中，待有线程空闲时，便处理
 *  在任务队列中的任务。
 */
public class FixedThreadPoolDemo_2_1 {
    public static class MyTask implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("CPU数量：" + Runtime.getRuntime().availableProcessors());
        MyTask myTask = new MyTask();
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(int i = 0;i < 10;i++){
            service.submit(myTask);
        }
    }
}
