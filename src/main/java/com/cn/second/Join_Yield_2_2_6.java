package com.cn.second;

/**
 *  2.2.6  等待线程结束(join) 和 谦让(yield)
 *  public final void join() throws InterruptedException
 *  这个方法表示无限等待，他会一直阻塞当前线程，直到目标线程执行完毕
 *  public final synchronized void join(long millis) throws InterruptedException
 *  这个方法给出了一个最大的等待时间，如果超过给定时间目标线程还在执行，当前线程也会因为“等不及了”，而继续往下执行
 *
 *  join() 的本质是让调用线程 wait() 在当前线程对象实例上。
 *  源码 ：
 *      while (isAlive()) {
 *          wait(0);
*       }
 *      可以看到，他让调用线程在当前线程对象上进行等待。当线程执行完成后，被等待的线程会在退出前调用 notifyAll()
 *      通知所有的等待线程继续执行。
 *      因此，值得注意的一点是：
 *          不要再应用程序中，在Thread 对象实例上使用类似 wait() 或者 notify() 等方法，
 *          因为这很有可能会影响系统API的工作，或者被系统API所影响。
 *
 *  Thread.yield()
 *      public static native void yield();
 *      这是一个静态方法，一旦执行，他会使当前线程让出CPU。但要注意，让出CPU并不表示当前线程不执行了。
 *  当前线程在让出CPU后，还会进行CPU资源争夺，但是是否能够再次被分配到，就不一定了。
 *      因此，对 Thread.yield() 的调用就好像再说：我已经完成一些最要的工作了，我应该是可以休息了，
 *  可以给其他线程一些工作机会啦！
 *  如果你觉的一个线程不那么重要，或者优先级非常低，而且又害怕他会占用太多CPU资源，
 *  那么可以在是当的时候调用Thread.yield(),给予其他重要线程更多的工作机会。
 *
 *
 */
public class Join_Yield_2_2_6 {
    public volatile static int i = 0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for(i = 0; i < 1000000; i++){}
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        addThread.start();
        addThread.join();
        System.out.println(i);
    }
}
