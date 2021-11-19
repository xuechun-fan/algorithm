package com.fxc.algorithm.problem.多线程;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Semaphore测试类
 * - 实现限流的功能
 *
 * @author FXC
 */
public class SemaphoreTest {

    /** 并发数量 */
    private static final int NUM = 10;
    /** 创建Semaphore实例 */
    private static final Semaphore LIMIT = new Semaphore(3);
    /** 创建CyclicBarrier实例来模拟并发 */
    private static final CyclicBarrier BARRIER = new CyclicBarrier(NUM + 1);
//    /** 创建CountDownLatch实例来模拟开关功能 */
//    private static final CountDownLatch LATCH = new CountDownLatch(1);
    /** 随机数生成工具 */
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        new SemaphoreTest().test();
    }

    public void test() {

        for (int i = 1; i <= NUM; i++) {
            new Thread(this::park, String.valueOf(i)).start();
        }

        // 主线程忙等车辆线程准备完毕后再释放最后开关
        while (BARRIER.getNumberWaiting() < NUM) { }

        try {
            // 最后一个步骤完成，发出开抢信号
            BARRIER.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("\n所有车辆均已就位，开抢车位 *** \n");

    }

    /**
     * 停车逻辑模拟停车场限流场景
     */
    private void park() {

        try {
            // 等待所有线程到达停车场，然后并发抢车位
            TimeUnit.SECONDS.sleep(RANDOM.nextInt(2));
            System.out.println("车辆[" + Thread.currentThread().getName() + "] 来到停车场...");
            BARRIER.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        try {
            LIMIT.acquire();
            System.out.println("车辆[" + Thread.currentThread().getName() + "] 停车成功 >>> ");
            TimeUnit.SECONDS.sleep(RANDOM.nextInt(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("车辆[" + Thread.currentThread().getName() + "] 驶出停车场 <<< ");
            LIMIT.release();
        }
    }
}
