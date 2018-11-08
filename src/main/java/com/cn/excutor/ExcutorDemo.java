package com.cn.excutor;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ExcutorDemo  implements Runnable{

    private String name;

    public ExcutorDemo(String name){
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
        int corePoolSize = 10,maximumPoolSize = 10;
        long keepAliveTime = 10L;
        ArrayBlockingQueue runnableTaskQueue = new ArrayBlockingQueue(5);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,TimeUnit.SECONDS,runnableTaskQueue);
        for(int i = 0; i < 20; i++){
            executor.execute(new ExcutorDemo("任务" + i));
        }
    }
}
