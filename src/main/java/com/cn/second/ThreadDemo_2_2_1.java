package com.cn.second;

public class ThreadDemo_2_2_1 {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            public void run(){
                System.out.println("hello Thread");
            }
        };
        thread.start();
    }
}
