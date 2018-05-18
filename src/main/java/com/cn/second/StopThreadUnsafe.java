package com.cn.second;

/**
 * 2.终止线程
 * Thread.stop()方法在结束线程时，会直接终止线程，并且会立即释放这个线程所持有的的锁。
 * 而这些锁恰恰是用来维持对象一致性的。如果此时，写线程写入数据正写到一半，并强行终止，
 * 那么对象就会被写怀，同时，由于锁已经被释放，另外一个等待该锁的读线程就顺利成章的
 * 读到了这个不一致的对象，悲剧也就此发生。
 *
 * 如果要停止一个线程，应该怎么做？
 * 方法：需要有我们自行决定线程何时推出就可以了。
 * 在下面代码中，添加了
 *         volatile  boolean stopme = false;
 *         public void stopMe(){
 *             stopme = true;
 *         }
 *
 *          if(stopme){
*               System.out.println("exit by stop me");
*               break;
*            }
 */
public class StopThreadUnsafe {

    public  static User u = new User();
    public static class User{
        private int id;
        private String name;
        public User(){
            id = 0;
            name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public  static class ChangeObjectThread extends Thread{
        /**
         * 这段代码时正确终止线程的 start
         */
        //定义该变量线程是否要退出
        volatile  boolean stopme = false;
        //当 stopMe 方法被调用，stopme 就被设置为 true，当代码第18行检测到这个改动时，
        //线程就自然退出了。
        //使用这种方式推出线程，不会使对象u的状态出现错误
        public void stopMe(){
            stopme = true;
        }
        /**
         * end
         */
        public void run(){
            while(true){
                /**
                 * 这段代码时正确终止线程的 start
                 */
                if(stopme){
                    System.out.println("exit by stop me");
                    break;
                }
                /**
                 * end
                 */
                synchronized (u){
                    int v = (int)(System.currentTimeMillis()/1000);
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }
    public static class ReadObjectThread extends Thread{
        public void run(){
            while (true){
                synchronized (u){
                    if(u.getId() != Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while(true){
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(100);
//            t.stop();
            ((ChangeObjectThread) t).stopMe();
        }
    }
}
