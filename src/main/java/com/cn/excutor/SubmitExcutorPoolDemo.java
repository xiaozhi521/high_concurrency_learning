package com.cn.excutor;

import java.util.concurrent.*;

public class SubmitExcutorPoolDemo {

    static  BlockingQueue<Runnable> blockingQueue = null;
    static  ThreadPoolExecutor poolExecutor = null;
    static {
        blockingQueue = new LinkedBlockingQueue<>(1);
        poolExecutor = new ThreadPoolExecutor(1,2,3L,TimeUnit.SECONDS,blockingQueue);
    }
    public static void main(String[] args) throws Exception {
        // 有参返回  Callable
        Future future = poolExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello Thread";
            }
        });
        String futureStr = (String) future.get();
        //Callable》》》Hello Thread
        System.out.println("Callable》》》" + futureStr);

        //实现 Runnable 无参返回
        Future runnable = poolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("实现Runnable》》》Hello Runnable");
            }
        });
//        实现Runnable》》》Hello Runnable
//        实现Runnable》》》null
        System.out.println("实现Runnable》》》" + runnable.get());

        //实现 Runnable 有参返回
        Future runnable2 = poolExecutor.submit(new Runnable(){
            @Override
            public void run() {
                System.out.println("runnable2》》》11111111" );
            }
        },"hello runnable2");

//        runnable2》》》11111111
//        runnable2》》》hello runnable2
        System.out.println("runnable2》》》" + runnable2.get());


        poolExecutor.shutdown();
    }
}
