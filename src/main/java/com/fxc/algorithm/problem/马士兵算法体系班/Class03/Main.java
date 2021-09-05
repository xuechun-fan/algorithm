package com.fxc.algorithm.problem.马士兵算法体系班.Class03;

import org.junit.Test;

/**
 * @author chun
 */
public class Main {

    /**
     * 数组中如何不使用额外变量交换0、1下标对应的两个数
     *
     * @param a
     * @param b
     * @return
     */
    public void swap(int a, int b) {
        // 为了方便先取出来
        // 不使用额外变量交换a、b两个数字
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + " - " + b);
    }

    /**
     * 数组中只有一个数出现了奇数次，其他都是偶数次，请找到这个数并返回
     *
     * @param nums
     * @return
     */
    public int findTheOdd(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    /**
     * 提取出给定数字二进制表达中最右侧的数值
     * 如：
     * 输入：0000 1101 0001 0000
     * 输出：0000 0000 0001 0000
     *
     * @param num
     * @return
     */
    public int getTheRightest1(int num) {
        return num & (-num);
        //        int a = 1;
        //        while ((num & a) == 0) {
        //            a <<= 1;
        //        }
        //        return a;
    }

    /**
     * 一个数组中只有两种数出现了奇数次，其他数字都出现了偶数次，
     * 找出这两种数字并打印出来
     *
     * @param nums
     */
    public void findAllOdd(int[] nums) {
        int p1 = 0;
        int p2 = 0;
        int t = 0;
        for (int num : nums) {
            t ^= num;
        }
        // 此时： t = odd1 ^ odd2
        p1 = 1;
        while ((t & p1) == 0) {
            p1 <<= 1;
        }
        t = p1;
        p1 = 0;
        for (int num : nums) {
            if ((num & t) == 0) {
                p1 ^= num;
            } else {
                p2 ^= num;
            }
        }
        System.out.println(p1 + " " + p2);
    }

    @Test
    public void test() {
        int[] nums = {1, 1, 1, 2, 2, 3, 3, 4, 6, 6, 6, 6};
        findAllOdd(nums);
    }
}
