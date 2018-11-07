package com.cn.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/***
 *  ①ForkJoinTask：我们要使用ForkJoin框架，必须首先创建一个ForkJoin任务。它提供在任务
 *  中执行fork()和join()操作的机制。通常情况下，我们不需要直接继承ForkJoinTask类，只需要继
 *  承它的子类，Fork/Join框架提供了以下两个子类。
 *      ·RecursiveAction：用于没有返回结果的任务。
 *      ·RecursiveTask：用于有返回结果的任务。
 * ②ForkJoinPool：ForkJoinTask需要通过ForkJoinPool来执行。
 *  任务分割出的子任务会添加到当前工作线程所维护的双端队列中，进入队列的头部。当
 *  一个工作线程的队列里暂时没有任务时，它会随机从其他工作线程的队列的尾部获取一个任务。
 */
public class CountTask extends RecursiveTask<Integer> {
    // 阈值
    private static final int THRESHOLD = 5;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();
            // 等待子任务执行完，并得到其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务，负责计算1+2+3+4+ .... +10
        CountTask task = new CountTask(1, 20);
        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        }
        Long start2 = System.currentTimeMillis();
        System.out.println("ForkJoin >>>>>" + (start2 - start));
        int suma = 0;
        for (int i = 0; i <= 10; i++) {
            suma += i;
        }
        Long start3 = System.currentTimeMillis();
        System.out.println("main >>>>>" + (start3 - start2));
    }
}
