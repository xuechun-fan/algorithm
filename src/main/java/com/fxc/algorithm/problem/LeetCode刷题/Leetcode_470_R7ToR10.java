package com.fxc.algorithm.problem.LeetCode刷题;

import java.util.Random;

/**
 * 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
 * <p>
 * 不要使用系统的 Math.random() 方法。
 * </p>
 *
 * @author chun
 * @date 2021/9/5 1:06
 */
public class Leetcode_470_R7ToR10 {

    public int rand10() {
        while (true) {
            // r49
            int num = (rand7() - 1) * 7 + rand7();
            if (num <= 40) {
                return (num % 10) + 1;
            }
            // r63
            num = (num - 40 - 1) * 7 + rand7();
            if (num <= 60) {
                return (num % 10) + 1;
            }
            // r21
            num = (num - 60 - 1) * 7 + rand7();
            if (num <= 20) {
                return (num % 10) + 1;
            }
        }
    }

    private int rand7() {
        return new Random().nextInt(7);
    }
}
