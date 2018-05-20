package com.cn.third.SynchronouControl_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *  3.1.3  允许多个线程同时访问： 信号量（Semaphore）
 *
 *  信号量为多线程协作提供了更为强大的控制方法。广义上说：信号量是对锁的扩展。无论是内部锁 synchronized 还是重入锁
 *  ReentrantLock，一次都只允许一个线程访问一个资源，而信号量却可以指定多个线程，同时访问某一个资源。
 *  信号量主要提供了一下构造函数：
 *      public Semaphore(int permits)
 *      public Semaphore(int permits，boolean fair)
 *  在构造信号量对象中，必须要指定信号量的准入数，即同时能申请多少个许可。当每个线程每次只申请一个许可时，
 *  这就相当于制定了同时有多少个线程可以访问某一个资源。信号量的主要逻辑方法有：
 *      public void acquire() throws InterruptedException
 *      public void acquire(int permits) throws InterruptedException
 *          该方法尝试获得一个准入的许可。若无法获得，则线程会等待，直到有线程释放一个许可或者当前线程被中断。
 *      public void acquireUninterruptibly(int permits)
 *          该 方法和 acquire()方法类似，但是不响应中断。
 *      public boolean tryAcquire()
 *      public boolean tryAcquire(long timeout,TimeUnit unit) throws InterruptedException
 *          该方法尝试获得一个许可，如果成功返回true，失败则返回false，他不会进行等待，立即返回。
 *      public void release()
 *          该方法在线程访问资源结束后，释放一个许可，以使其他等待许可的线程可以进行资源访问。
 *
 */
public class Semaphore_3_1_3 implements Runnable{
    //声明一个包含5个许可的信号量，这就意味着同时可以有5个线程进入代码段 38，39 行
    final Semaphore semaphore = new Semaphore(5);
    @Override
    public void run() {
        try {
            //申请信号量：尝试一个准入的许可，若无法获得，就会进行线程等待
            semaphore.acquire();
            // 模拟耗时操作
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + "; " + Thread.currentThread().getName() + ": done");
            //释放信号量：
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final Semaphore_3_1_3  semaphore_3_1_3 = new Semaphore_3_1_3();
        for(int i = 0; i < 20; i++){
            executorService.submit(semaphore_3_1_3);
        }
    }
}
