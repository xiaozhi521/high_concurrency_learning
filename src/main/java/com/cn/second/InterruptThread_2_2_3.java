package com.cn.second;

/**
 *  2.2.3 线程中断
 *  线程中断并不会使线程立即退出，而是给线程发送一个通知，告知目标线程，有人希望你退出。
 *  至于目标线程接到通知后如何处理，则完全由目标线程自行解决。
 *  这点很重要，如果线程中断后，线程立即无条件退出，我们就会遇到stop方法的老问题。
 *  与线程有关的三个方法：
 *      public void Thread.interrupt()              //中断线程
 *      public boolean Thread.isInterrupted()       //线程是否被中断
 *      public static boolean Thread.interrupted()  //判断是否被中断，并清除当前中断状态
 *      Thread.interrupt() 方法是一个实例方法。他通知目标线程中断，也是设置中断标志位。
 *          中断标志位表示当前线程已经被中断了。
 *      Thread.isInterrupted()方法也是实例方法。它判断当前线程是否被中断（通过检查中断标志位）。
 *      Thread.interrupted() 方法时静态方法。 用来判断当前线程的中断状态，但同时会清除当前线程的中断标志位状态。
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
