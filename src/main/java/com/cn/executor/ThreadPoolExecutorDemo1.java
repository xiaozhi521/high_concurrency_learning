package com.cn.executor;


import java.util.concurrent.*;


/**
 *  有界队列 excute() 无参返回
 *  设置线程池 corePoolSize 基本大小为10，maximumPoolSize 最大为15，runnableTaskQueue 排队任务为5，
 *  当任务数量大于 runnableTaskQueue 时，会抛出以下异常
 *      Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task com.cn.excutor.ExcutorDemo@511d50c0 rejected from
 *      java.util.concurrent.ThreadPoolExecutor@60e53b93[Running, pool size = 15, active threads = 15, queued tasks = 6, completed tasks = 0]
 * 	    at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2047)
 * 	    at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:823)
 * 	    at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1369)
 * 	    at com.cn.excutor.ExcutorDemo.main(ExcutorDemo.java:37)
 * 	解决方案：
 * 	    1、增大 maximumPoolSize ，例如设置为Integer.MAX_VALUE
 * 	    2、 设置合理的 BlockingQueue 长度， 保证 maximumPoolSize + BlockingQueue 大于任务的总数
 */
public class ThreadPoolExecutorDemo1  implements Runnable{

    private String name;

    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    public ThreadPoolExecutorDemo1(String name){
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
        int corePoolSize = 10,maximumPoolSize = 15;
        long keepAliveTime = 10L;
        BlockingQueue<Runnable> runnableTaskQueue = new ArrayBlockingQueue(6);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,TimeUnit.SECONDS,runnableTaskQueue);


        for(int i = 0; i < 20; i++){
            if(!executor.isShutdown()){
                executor.execute(new ThreadPoolExecutorDemo1("任务" + i));
            }
        }
        Future future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello ThreadPool";
            }
        });
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }
}
