package com.cn.second;

import java.sql.SQLOutput;

/**
 * 2.4分门别类的管理：线程组
 *  在一个系统中，如果线程数量很多，而且功能分配比较名确，就可以将相同
 *  功能的线程放置在一个线程组里。
 *  打个比方：如果你有一个苹果，你就可以把他拿在手里，但是如果你有十个苹果
 *  你就最好还有一个篮子，否则不方便携带。对于多线程来说，也是这个道理。
 *  想要轻轻松松处理几十个甚至上百个线程，最好还是将他们都装进对应的篮子里
 *
 *  线程组还有一个值得注意的方法 stop(),他会停止线程组中的所有线程。
 *  这看起来是一个很方便的功能，但是他会遇到和Thread.stop() 相同的问题，因此
 *  使用时也需要格外的谨慎。
 */
public class ThreadGroup_2_4 implements Runnable{
    public static void main(String[] args) {
        //创建一个名为 printGroup 的线程组
        ThreadGroup th = new ThreadGroup("printGroup");
        //创建两个线程，使用Thread的构造函数，指定线程所属的线程组，将线程和线程组关联起来
        Thread t1 = new Thread(th,new ThreadGroup_2_4(),"T1");
        Thread t2 = new Thread(th,new ThreadGroup_2_4(),"T2");
        t1.start();
        t2.start();
        //activeCount() 可以获得活动线程的总数，但由于线程是动态的，因此
        //这个值只是一个估值，无法确定精确
        System.out.println(th.activeCount());
        //list() 方法可以打印这个线程组中所有的线程信息，对调试有一定帮助
        th.list();
    }
    @Override
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName()
                + "-" + Thread.currentThread().getName();
        while (true){
            System.out.println("i am " + groupAndName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
