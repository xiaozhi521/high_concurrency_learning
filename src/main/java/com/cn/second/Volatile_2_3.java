package com.cn.second;

/**
 * 2.3 volatile 与 java 内存模型（JMM）
 * JMM：都是围绕着原子性、有序性和可见性展开的。
 * java使用了一些特殊的操作或者关键字来声明、告诉虚拟机，在这个地方，要尤其注意，
 * 不能随意变动优化目标指令。关键字 volatile 就是其中之一。
 *
 * 当用 volatile 去申明一个变量时，就等于告诉了虚拟机，这个变量极有可能会被某些
 * 程序或者线程修改。为了确保这个变量呗修改后，应用程序范围内的所有线程都能拿够
 * “看到“这个改动，虚拟机就必须采用一些特殊的手段，保证这个变量的可见性等特点。
 */
/**
 * volatile 对于保证操作的原子性是有非常大的帮助的。
 * 但是需要注意的是，volatile并不能代替锁，他也无法保证一些复合操作的原子性。
 *
 * 下面的例子是 volatile 是无法保证i++ 的原子性操作的。
 */
public class Volatile_2_3 {
    static volatile int i = 0;


    public static class PlusTask implements Runnable{
        @Override
        public void run() {
            for(int k = 0; k < 100000; k++)
                i++;
        }
    }
    //如果 i++ 是原子性的，那么最终的值应该是 100000（10个线程个累加10000次）
    //但实际上，上述代码的输出总是会小于 100000.
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for(int i = 0; i < 10; i++){
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
        }
        for(int i = 0; i < 10; i++){
            threads[i].join();
        }
        System.out.println(i);
    }
}
