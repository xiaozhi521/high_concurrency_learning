package com.cn.third.execotors_2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *  2.计划任务
 *  newScheduledThreadPool()方法：该方法返回一个ScheduledExecutorService 对象，但该线程可以指定线程数量。
 */
public class ScheduleDemo_2_2 {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        //如果前面的任务没有完成，则调度也不会启动
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("scheduleWithFixedDelay: " + System.currentTimeMillis() / 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },0,3,TimeUnit.SECONDS);
        //如果前面的任务没有完成，则调度也不会启动
        //该方法如果周期太短，那么任务就会在上一个任务结束后，立即被调用。
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("scheduleAtFixedRate: " + System.currentTimeMillis() / 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },0,1,TimeUnit.SECONDS);
    }
}
