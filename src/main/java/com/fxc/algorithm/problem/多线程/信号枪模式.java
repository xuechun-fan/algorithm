package com.fxc.algorithm.problem.多线程;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 实现多个线程同时启动
 * 1、CyclicBarrier
 * 2、CountdownLatch
 *
 * @author FXC
 */
public class 信号枪模式 {

    private static final int NUM = 10;

    private CyclicBarrier barrier = new CyclicBarrier(NUM);

    private CountDownLatch latch = new CountDownLatch(NUM);

    public static void main(String[] args) throws InterruptedException {
        信号枪模式 test = new 信号枪模式();
        // test1
        //        test.cyclicBarrierWay(NUM);

        // test2
        test.countDownLatchWay(NUM);
    }

    /**
     * CyclicBarrier方式实现
     *
     * @param num 线程数量
     */
    public void cyclicBarrierWay(int num) {

        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                try {
                    initSingletonDemo1();
                } catch (BrokenBarrierException | InterruptedException e) {
                    e.printStackTrace();
                }
            }, "t" + i).start();
        }

    }


    /**
     * CountdownLatch方式实现
     *
     * @param num 线程数量
     */
    public void countDownLatchWay(int num) throws InterruptedException {
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                try {
                    initSingletonDemo2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "t" + i).start();
        }
        //        initSingletonDemo3();
    }


    private void initSingletonDemo1() throws BrokenBarrierException, InterruptedException {
        //        System.out.println(Thread.currentThread().getName() + " 线程准备...");
        barrier.await();
        //        System.out.println(Thread.currentThread().getName() + " 线程开始执行...");
        SingletonDemo instance = SingletonDemo.getInstance();
        System.out.println(SingletonDemo.getInstance().getTime() + " " + Thread.currentThread()
                                                                               .getName() + "线程");
    }

    private void initSingletonDemo2() throws InterruptedException {
        latch.countDown();
        //        System.out.println(Thread.currentThread().getName() + " 线程准备...");
        latch.await();
        //        System.out.println(Thread.currentThread().getName() + " 线程开始执行...");
        SingletonDemo instance = SingletonDemo.getInstance();
        System.out.println(SingletonDemo.getInstance().getTime() + " " + Thread.currentThread()
                                                                               .getName() + "线程");
    }

    private void initSingletonDemo3() throws InterruptedException {
        System.out.println(SingletonDemo.getInstance().getTime() + " " + Thread.currentThread()
                                                                               .getName() + "线程");
    }

}

/**
 * 单例模式测试类
 *
 * @author FXC
 */
class SingletonDemo {

    private static SingletonDemo instance;

    private String time;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + " 线程 --- 私有构造方法初始化...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.time = String.valueOf(System.currentTimeMillis());
    }

    /**
     * 对外获取单例接口
     *
     * @return 初始化好的实例对象
     */
    public static SingletonDemo getInstance() {
        if (instance == null) {

            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
