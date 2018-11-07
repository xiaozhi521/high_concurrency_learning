package com.cn.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(8);
        int a = atomicInteger.get();
        System.out.println("获取原始值" + a);
        atomicInteger.set(11);
        int a1 = atomicInteger.get();
        System.out.println("获取修改后的值" + a1);
        int a2 = atomicInteger.getAndSet(10);
        System.out.println("设置新值获取原始值" + a2);
        int a21 = atomicInteger.get();
        System.out.println("获取原始新值" + a21);
        int a3 = atomicInteger.getAndAdd(10);
        System.out.println("原始值与新值相加并返回原始值：" + a3);
        int a22 = atomicInteger.get();
        System.out.println("获取相加后的新值" + a22);
        int a4 = atomicInteger.incrementAndGet();
        System.out.println("原始值自增：" + a4);
        System.out.println("转换成float类型："+atomicInteger.floatValue());
        System.out.println("转换成double类型："+atomicInteger.doubleValue());
    }
}
