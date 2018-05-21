package com.cn.second;

import java.util.HashMap;
import java.util.Map;

/**
 * 2.8.2 HashMap 不是线程安全的，多线程访问HashMap时，也可能会遇到意想不到的错误，错误比ArrayList更诡异
 *
 *  注意：下面的代码谨慎运行，由于这段代码很可能占用两个CPU核，并使他们的 CPU 占有率达 100% 。
 *         如果CPU 性能较弱，可能导致死机，请先保存资料，在进行尝试。
 *
 * 下面程序我们期望得到的map.size 就是100000.但实际上，你可能会得到以下但种情况：
 *  第一：程序正常结束，并且结果也是符合预期的。hashMap 的大小为 100000
 *  第二：程序正常结束，但结果不符合预期，而是一个小于 100000 的数字，比如：99835
 *  第三：程序永远无法结束
 *
 *  前两种情况与 ArrayList 情况非常类似。
 *
 */
public class Exception_HashMap_2_8_2 {
    static Map<String,String> map = new HashMap<String,String>();

    public static class AddThread implements Runnable{
        int start = 0;
        public AddThread(int start){
            this.start = start;
        }

        @Override
        public void run() {
            for(int i = start;i < 100000; i+=2){
                map.put(Integer.toString(i),Integer.toString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread(0));
        Thread t2 = new Thread(new AddThread(1));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
    }
}
