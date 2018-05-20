package com.cn.third.SynchronouControl_1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  3.1.4  ReadWriteLock 读写锁
 *  ReadWriteLock 是JDK5中提供的读写分离锁。
 *  读写分离锁可以有效的帮助减少锁竞争，以提升系统性能。比如：线程A1、A2、A3进行写操作，B1、B2、B3进行写操作，如果
 *  使用重入锁或者内部锁，则理论上说所有读之间、读与写之间、写和写之间都是串行操作。当B1进行读取时，B2、B3则需要
 *  等待锁。由于读操作并不对数据的完整性造成破坏，这种等待显然是不合理。因此，读写锁有了发挥功能的余地。
 *      在这种情况下，读写锁允许多个线程同时读，使得B1、B2、B3之间真正并行。但是，考虑到数据的完整性，谢谢操作和读写
 *  操作依然是需要相互等待和持有锁的。总的来说，读写锁的访问约束如下：
 *              读           写
 *  读       非阻塞         阻塞
 *  写       阻塞           阻塞
 *  读-读不互斥：读读之间不阻塞
 *  读-写互斥：读写阻塞，写也会阻塞读
 *  写-写互斥：写写阻塞
 *
 *  如果在系统中，读操作次数远远大于写操作，则读写锁就可以发挥最大的功效，提升系统的性能。
 *
 *
 */
public class ReadWriteLock_3_1_4 {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
//    private static Lock readLock

}
