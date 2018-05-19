package com.cn.second;

import java.util.ArrayList;

/**
 * ArrayList 是一个线程不安全的容器。如果在多线程中使用 ArrayList， 可能会导致程序出错。
 * 执行下面这段代码，可能会出现三种结果：
 *      1.程序正常结束，ArrayList 的最终大小确实 200000.这说明即使并行程序有问题，也未必会每次表现出来
 *      2.程序抛异常：
 *          Exception in thread "Thread-1" java.lang.ArrayIndexOutOfBoundsException: 15
 *              at java.util.ArrayList.add(ArrayList.java:459)
 *              at com.cn.second.ArrayList_2_8_1$AddThread.run(ArrayList_2_8_1.java:21)
 *              at java.lang.Thread.run(Thread.java:745)
 *        这是因为 ArrayList 在扩容过程中，内部一致性被破坏，但由于没有锁的保护，
 *        另外一个线程访问到了不一致的内部状态，导致出现越界问题。0
 *      3.出现了一个非常隐蔽的错误，比如打印如下值作为ArrayList的大小：198672
 *
 *      显然，这是由于多线程访问冲突，使得保存容器大小的变量被多线程不正常的访问，同时两个线程也同时对ArrayList
 *      中同一个位置进行赋值导致的。改进方法：使用线程安全的Vector代替 ArrayList即可。
 *
 */
public class Exception_ArrayList_2_8_1 {
        static ArrayList<Integer> a1 = new ArrayList<Integer>(10);
//    static Vector<Integer> a1 = new Vector<Integer>(10);
        public static class AddThread implements Runnable{
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++){
                    a1.add(i);
                }
            }
        }

    public static void main(String[] args) throws InterruptedException {
//        for(int a = 0; a < 50 ; a++){
            Thread t1 = new Thread(new AddThread());
            Thread t2 = new Thread(new AddThread());
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(a1.size());
//        }

    }
}
