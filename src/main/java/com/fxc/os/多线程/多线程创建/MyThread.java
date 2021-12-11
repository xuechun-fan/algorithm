package com.fxc.os.多线程.多线程创建;

/**
 * 多线程创建方式1: 编写一个类，继承Thread，并重写run()方法
 *
 * @author FXC
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("我的自定义线程正在运行...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("新线程运行完毕...");
    }
}