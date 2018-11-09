package com.cn.thread_pool_executor;

import java.util.concurrent.*;

/**
 *  无界队列
 *  CachedThreadPool 是大小无界的线程池，
 *  适用于执行很多的短期异步任务的小程序，或者是负载较轻的服务器
 */
public class ExcutorThrealPoolDemo implements Runnable {

    private String name;

    ExcutorThrealPoolDemo(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //创建无界线程池
        ExecutorService cachedThreadPool =  Executors.newCachedThreadPool();
        for (int i = 0;i < 5;i++){
            cachedThreadPool.execute(new ExcutorThrealPoolDemo("任务"+i));
        }
        cachedThreadPool.shutdown();
    }


}
