package com.cn.second;

/**
 *  线程的优先级
 *
 *  Thread 三个静态标量：
 *      public final static int MIN_PRIORITY = 1;
 *      public final static int NORM_PRIORITY = 5;
 *      public final static int MAX_PRIORITY = 10;
 *  数字越大则优先级越高，但有效范围在1到10之间。
 */
public class PriorityDemo_2_6 {
    public static class HighPriority extends Thread{
        static int count = 0;

        @Override
        public void run() {
            while(true){
                synchronized (PriorityDemo_2_6.class){
                    count++;
                    if(count > 1000){
                        System.out.println("HightPriority is complete");
                        break;
                    }
                }
            }
        }
    }
    public static class LowPriority extends Thread{
        static int count = 0;

        @Override
        public void run() {
            while(true){
                synchronized (PriorityDemo_2_6.class){
                    count++;
                    if(count > 1000){
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        HighPriority highPriority = new HighPriority();
        LowPriority lowPriority = new LowPriority();
        highPriority.setPriority(Thread.MAX_PRIORITY);
        lowPriority.setPriority(Thread.MIN_PRIORITY);
        lowPriority.start();
        highPriority.start();
    }
}
