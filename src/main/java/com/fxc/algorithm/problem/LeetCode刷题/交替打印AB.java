package com.fxc.algorithm.problem.LeetCode刷题;

/**
 * Description:
 */
public class 交替打印AB {
    public static void main(String[] args) {
        Word word = new Word();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (word) {
                        if ("A".equals(word.getS())) {
                            System.out.println(word.getS());
                            word.setS("B");
                            word.notify();
                        } else {
                            try {
                                word.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (word) {
                        if ("B".equals(word.getS())) {
                            System.out.println(word.getS());
                            word.setS("A");
                            word.notify();
                        } else {
                            try {
                                word.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}

class Word {
    private String s = "A";

    public Word() {
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}