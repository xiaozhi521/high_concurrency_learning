package com.cn.executor;

import java.util.concurrent.*;

/**
 *  无界队列
 *
 */
public class ThreadPoolExecutorDemo2 implements Runnable {

    private String name;

    ThreadPoolExecutorDemo2(String name){
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
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,2,3L,TimeUnit.SECONDS,blockingQueue);
        for (int i = 0;i < 3;i++){
            poolExecutor.execute(new ThreadPoolExecutorDemo2("任务"+i));
        }
        poolExecutor.shutdown();
    }


}
