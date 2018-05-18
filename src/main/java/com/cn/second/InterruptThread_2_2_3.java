package com.cn.second;

/**
 * 线程中断
 *
 */
public class InterruptThread_2_2_3 {
    public static void main(String[] args) throws InterruptedException {
        InterruptThread_2_2_3.interruptThreadMethod2();
    }

    /**
     *
     * @throws InterruptedException
     * Thread.sleep() 方法由于中断而抛出异常，此时，他会清除中断标记，如果不加处理，
     *  那么在下次循环开始时，就无法捕获这个中断，故在异常处理中，再次设置中断标记。
     */
    public static void interruptThreadMethod2() throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("is interrupt");
                        break;
                    }
                    //Thread.sleep() 方法 会抛出 InterruptedException 中断异常
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("interrupt when sleep");
                       //设置中断状态
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        };
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
    public static void interruptThreadMethod1() throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("is interrupt");
                        break;
                    }
                    System.out.println("is not interrupt");
                    Thread.yield();
                }
            }
        };
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
