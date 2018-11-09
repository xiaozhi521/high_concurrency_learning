package com.cn.future;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Long start = System.currentTimeMillis();
        FutureTask futureTask = new FutureTask(new Callable<Map<String,String>>() {
            @Override
            public Map call() throws Exception {
                System.out.println("模拟执行业务逻辑");
                ConcurrentHashMap map = new ConcurrentHashMap();
                map.put("math","98");
                return map;
            }
        });
        futureTask.run();
        System.out.println(futureTask.get());
        Long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }
}
