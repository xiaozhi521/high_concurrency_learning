package com.cn.second;

public class ThreadRunableDemo_2_2_1 implements Runnable{
    @Override
    public void run() {
        System.out.println("hello thread");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadRunableDemo_2_2_1());
        thread.start();
    }
}
