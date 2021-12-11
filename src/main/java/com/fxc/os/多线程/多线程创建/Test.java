package com.fxc.os.多线程.多线程创建;

/**
 * 测试类
 *
 * @author FXC
 */
public class Test {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("创建新线程完毕...");
        try {
            Thread.sleep(500);
            System.out.println("main 等待新线程运行完毕...");
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main finished...");
    }
}