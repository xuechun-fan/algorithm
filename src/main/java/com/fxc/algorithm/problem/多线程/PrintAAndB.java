package com.fxc.algorithm.problem.多线程;

/**
 * 两个线程交替打印1-20的奇数和偶数
 *
 * @author chun
 * @date 2021/9/3 1:42
 */
public class PrintAAndB {

    /** 标志位 */
    private boolean flag = true;
    /** 数值 */
    private int i = 1;

    /**
     * 主方法入口
     *
     * @param args javac参数
     */
    public static void main(String[] args) {
        new PrintAAndB().test();
    }

    public void test() {
        Object object = new Object();
        new Thread(() -> {
            while (i < 20) {
                synchronized (object) {
                    if (i % 2 == 0) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + i++);
                    object.notifyAll();
                }
            }

        }, "奇数线程 --- ").start();

        new Thread(() -> {
            while (i < 20) {
                synchronized (object) {
                    if (i % 2 == 1) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + i++);
                    object.notifyAll();
                }
            }
        }, "偶数线程 --- ").start();
    }

}
