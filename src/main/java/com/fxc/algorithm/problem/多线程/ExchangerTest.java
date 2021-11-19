package com.fxc.algorithm.problem.多线程;

import java.util.concurrent.Exchanger;

/**
 * Exchanger测试类
 * 用于两个线程之间交换信息。可以将Exchanger实例对象理解为一个包含两个格子的容器，通过该实例的exchange(V v)方法
 * 可以向两个格子种填充信息，当两个格子中均被填充后，该对象会自动将两个格子的信息交换，然后返回给线程，从而实现两个
 * 线程的信息交换。
 * 使用场景举例：
 * 比如游戏种两个玩家进行装备交换...
 *
 * @author FXC
 */
public class ExchangerTest {

    private static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) {

        new Thread(() -> {
            String s = "sequence 1";
            try {
                System.out.println("交换前 " + Thread.currentThread().getName() + "的 Sring s = " + s);
                s = EXCHANGER.exchange(s);
                System.out.println("交换后 " + Thread.currentThread().getName() + "的 Sring s = " + s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程1").start();

        new Thread(() -> {
            String s = "sequence 2";
            try {
                System.out.println("交换前 " + Thread.currentThread().getName() + "的 Sring s = " + s);
                s = EXCHANGER.exchange(s);
                System.out.println("交换后 " + Thread.currentThread().getName() + "的 Sring s = " + s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程2").start();

    }
}
