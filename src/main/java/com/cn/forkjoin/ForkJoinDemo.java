package com.cn.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/*  任务阀值    1   2   3   4   5   6   7   8   9   10
 *  消耗时间    11  6   6   4   4   3   3   4   4   3
 *
 *  命令模式
 *  ForkJoinTask需要实现 compute 方法
 *  在这个方法里，需要判断任务是否足够小，如果足够小直接执行任务
 *  如果不足够小，就必须分割成子任务，每个子任务在调用fork方法时，又会进入 compute 方法
 *  看看当前子任务时候需要继续分割成子任务，如果不需要继续分割，则会执行当前子任务并返回结果
 *  使用 join 方法会等待子任务执行完并得到结果
 */
public class ForkJoinDemo{
    // 阈值
    private static final int THRESHOLD = 5;

    /**
     *
     * @param start
     * @param end
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static Integer count(final int start, final int end) throws ExecutionException, InterruptedException {
        //创建一个有返回值的任务
        RecursiveTask<Integer> recursiveTask = new RecursiveTask() {
            @Override
            protected Integer compute() {
                int sum = 0;
                boolean canCompute = (end - start) <= THRESHOLD;
                if (canCompute) {
                    for (int i = start; i <= end; i++) {
                        sum += i;
                    }
                } else {
                    // 如果任务大于阈值，就分裂成两个子任务计算
                    int middle = (start + end) / 2;
                    int leftResult = 0,rightResult = 0;
                    try {
                        leftResult = ForkJoinDemo.count(start, middle);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        rightResult = ForkJoinDemo.count(middle + 1, end);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sum = leftResult + rightResult;
                }
                return sum;
            }
        };
        //创建ForkJoinPool，RecursiveTask需要通过ForkJoinPool执行
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(recursiveTask);
        return  result.get();
    }
    public static Integer count2(final int start, final int end) throws ExecutionException, InterruptedException {
        //创建一个有返回值的任务
        RecursiveTask<Integer> recursiveTask = new RecursiveTask() {
            @Override
            protected Integer compute() {
                int sum = 0;
                boolean canCompute = (end - start) <= THRESHOLD;
                if (canCompute) {
                    for (int i = start; i <= end; i++) {
                        sum += i;
                    }
                } else {
                    // 如果任务大于阈值，就分裂成两个子任务计算
                    int middle = (start + end) / 2;
                    int leftResult = 0,rightResult = 0;
                    try {
                        leftResult = ForkJoinDemo.count(start, middle);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        rightResult = ForkJoinDemo.count(middle + 1, end);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sum = leftResult + rightResult;
                }
                return sum;
            }
        };
        RecursiveTask<Integer> recursiveTask1 = new RecursiveTask() {
            @Override
            protected Integer compute() {
                int sum = 0;
                boolean canCompute = (end - start) <= THRESHOLD;
                if (canCompute) {
                    for (int i = start; i <= end; i++) {
                        sum += i;
                    }
                } else {
                    // 如果任务大于阈值，就分裂成两个子任务计算
                    int middle = (start + end) / 2;
                    int leftResult = 0,rightResult = 0;
                    try {
                        leftResult = ForkJoinDemo.count(start, middle);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        rightResult = ForkJoinDemo.count(middle + 1, end);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sum = leftResult + rightResult;
                }
                return sum;
            }
        };
        //创建ForkJoinPool，RecursiveTask需要通过ForkJoinPool执行
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(recursiveTask);
        Future<Integer> result1 = forkJoinPool.submit(recursiveTask1);
        return  result.get() + result1.get() ;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Long start = System.currentTimeMillis();
//        System.out.println(ForkJoinDemo.count(1,20));
        System.out.println(ForkJoinDemo.count2(1,10));
        Long start2 = System.currentTimeMillis();
        System.out.println("ForkJoin >>>>>" + (start2 - start));
    }
}
