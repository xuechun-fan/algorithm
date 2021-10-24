package com.fxc.algorithm.problem.设计模式._01_单例模式.懒汉式;

/**
 * Description：懒汉式，不同版本解析
 */
public class Test {
    public static void main(String[] args) {

    }
}


/**
 * 这种写法是不安全的
 */
class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        //  在多线程环境中，如果有多个线程同时访问到这里，那么就会创建出多个实例
        if (instance == null) {
            return new Singleton();
        }
        return instance;
    }
}

/**
 * 这种虽然表面上看保证了安全性，还降低了锁的粒度，但是由于JVM存在指令重排序优化功能，可能会出现错误
 */
class Singleton01 {
    private static Singleton01 instance;

    private Singleton01() {
    }

    public static Singleton01 getInstance() {
        //  双重检测方法
        if (instance == null) {
            synchronized (Singleton01.class) {
                if (instance == null) {
                    instance = new Singleton01();
                }
            }
        }
        return instance;
    }
}

/**
 * 基于上面的版本，我们让instance实例变量加上volatile关键字，实现了实例变量多线程间可见性，
 * 并且volatile关键字可以禁止指令重排序，（通过对变量的读、写前后加入store内存屏障来实现的），
 * 这样就保证了安全性。
 */
class Singleton01_1 {

    //    private volatile static Singleton01_1 instance;
    /**
     * 如果我们不使用volatile关键字来从虚拟机执行机制的层面来保证指令不被重排序，
     * 我们依旧可以使用临时变量的方法来强行增加依赖关系以达到不被重排序的目的
     */
    private static Singleton01_1 instance;

    private Singleton01_1() {
        System.out.println(Thread.currentThread().getName() + "\t单例构造方法");
    }

    public static Singleton01_1 getInstance() {
        if (instance == null) {
            synchronized (Singleton01_1.class) {
                if (instance == null) {
                    Singleton01_1 tmp = new Singleton01_1();
                    instance = tmp;
                }
            }
        }
        return instance;
    }
}

/**
 * 这种方式虽然保证了线程安全，但是加大了锁的粒度，效率很低
 */
class Singleton01_3 {
    private static Singleton01_3 instance;

    private Singleton01_3() {
    }

    public synchronized static Singleton01_3 getInstance() {
        if (instance == null) {
            instance = new Singleton01_3();
        }
        return instance;
    }
}


