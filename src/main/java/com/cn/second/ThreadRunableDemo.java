package com.cn.second;

public class ThreadRunableDemo implements Runnable{
    @Override
    public void run() {
        System.out.println("hello thread");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadRunableDemo());
        thread.start();
    }
}
