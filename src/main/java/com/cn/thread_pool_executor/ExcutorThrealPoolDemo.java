package com.cn.thread_pool_executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 无界队列
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
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(1);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,2,3L,TimeUnit.SECONDS,blockingQueue);

        for (int i = 0;i < 3;i++){
            poolExecutor.execute(new ExcutorThrealPoolDemo("任务"+i));
        }
        poolExecutor.shutdown();
    }


}
