package com.fxc.algorithm.problem.多线程;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 实现多线程的阶段性执行
 * 技术点：Phaser
 *
 * @author FXC
 */
public class PhaserTest {

    static Random r = new Random();

    static MarriagePhaser phaser = new MarriagePhaser();

    /**
     * 令当前线程睡眠相应时常
     *
     * @param milli 睡眠时常，单位为毫秒
     */
    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        phaser.bulkRegister(7);

        for (int i = 0; i < 5; i++) {
            new Thread(new Person("p" + i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    /**
     * 自定义Phaser子类
     *
     * @author FXC
     */
    static class MarriagePhaser extends Phaser {

        /**
         * 定义每个阶段的逻辑
         *
         * @param phase             传入阶段标志
         * @param registeredParties 当前线程数
         * @return
         */
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人到齐了！" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完了！" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了！" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束！新郎新娘抱抱！" + registeredParties);
                    return true;
                default:
                    return true;
            }
        }

    }

    static class Person implements Runnable {
        /** 名称 */
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.println(name + "到达现场！" + " 当前阶段" + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.println(name + "吃完！" + " 当前阶段" + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.println(name + "离开！" + " 当前阶段" + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
        }

        private void hug() {
            if (name.equals("新郎") || name.equals("新娘")) {
                milliSleep(r.nextInt(1000));
                System.out.println(name + "洞房！" + " 当前阶段" + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
            }
        }


        @Override
        public void run() {
            arrive();

            eat();

            leave();

            hug();
        }
    }

}
