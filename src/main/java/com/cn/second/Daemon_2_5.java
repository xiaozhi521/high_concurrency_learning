package com.cn.second;

/**
 * 2.5 驻守后台 : 守护线程(Daemon)
 * 守护线程是一种特殊的线程，他是系统的守护者，在后台默默地完成一些系统性服务，
 * 比如垃圾回收线程、JIT线程就可以理解为守护线程。与之对应的是用户线程，用户线程可以
 * 认为是系统工作线程，他会完成这个程序应该要完成的业务操作。如果用户线程全部结束，
 * 这也意味着这个程序实际上无事可做了。守护线程要守护的对象已经不存在了，那么整个
 * 应用程序就自然应该结束。因此，当一个java 应用内，只有守护线程时，java虚拟机就会
 * 自然退出。
 *
 * 将 thread 设置为守护线程，注意设置守护线程必须在线程 start() 之前设置
 * 否则你会得到一个类似以下的异常，告诉你守护线程设置失败。但是你的程序和
 * 线程依然可以正常执行。只是被当做用户线程而已。
 *
 * 因此：如果不小心忽略了下面的异常信息，你就很可能察觉不到这个错误，那你就会诧异为什么
 * 程序永远停不下来呢？
 * Exception in thread "main" java.lang.IllegalThreadStateException
 * 	at java.lang.Thread.setDaemon(Thread.java:1356)
 * 	at com.cn.second.Daemon_2_5.main(Daemon_2_5.java:34)
 *
 * 	下面的例子中 thread 被设置为守护线程，系统中只有主线程main 为用户线程，因此在 main 线程休眠2秒
 * 	后退出时，整个程序也随之结束。如果不把线程 thread 设置为守护线程， main 线程结束后，thread线程还会
 * 	不停的打印，永远不会结束。
 */
public class Daemon_2_5 {
    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while(true){
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new DaemonT();
        thread.start();
        thread.setDaemon(true);

        Thread.sleep(2000);
    }
}
