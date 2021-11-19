package com.fxc.algorithm.problem.多线程;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁测试类
 * - 读锁：共享锁，可重入
 * - 写锁：排他锁
 *
 * @author FXC
 */
public class ReadWriteLockTest {

    /** 普通排他锁 */
    private static final Lock LOCK = new ReentrantLock();
    /** 读写锁 */
    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();
    /** 读锁 */
    private static final Lock READ_LOCK = READ_WRITE_LOCK.readLock();
    /** 写锁 */
    private static final Lock WRITE_LOCK = READ_WRITE_LOCK.writeLock();
    /** 变量 */
    private static int value;

    public static void main(String[] args) {
        //        Runnable readR = () -> read(LOCK);
        Runnable readR = () -> read(READ_LOCK);

        //        Runnable writeR = () -> write(LOCK, 2);
        Runnable writeR = () -> write(WRITE_LOCK, 2);

        // 开启18个读线程
        for (int i = 0; i < 18; i++) {
            new Thread(readR).start();
        }
        // 开启2个写线程
        for (int i = 0; i < 2; i++) {
            new Thread(writeR).start();
        }
    }

    /**
     * 读逻辑
     *
     * @param lock 传入锁实例
     */
    public static void read(Lock lock) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("v = " + value + " read over...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 写逻辑
     *
     * @param lock 传入锁实例
     */
    public static void write(Lock lock, int v) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            value = v;
            System.out.println("v = " + value + " write over...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
