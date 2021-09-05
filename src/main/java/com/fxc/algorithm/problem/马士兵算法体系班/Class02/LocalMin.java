package com.fxc.algorithm.problem.马士兵算法体系班.Class02;

import org.junit.Test;

/**
 * 找到数组中局部最小的数值下标，定义类似波谷
 *
 * @author chun
 */
public class LocalMin {

    /**
     * 找到数组中局部最小的数值下标，定义类似波谷，返回一个即可
     *
     * @param arr 输入数组
     * @return 波谷下标
     */
    public int getLessIndex(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        int n = arr.length;
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[n - 1] < arr[n - 2]) {
            return n - 1;
        }
        int l = 1;
        int r = n - 2;
        int m = 0;
        while (l < r) {
            m = l + ((r - l) >> 1);
            if (arr[m] > arr[m - 1]) {
                r = m - 1;
            } else if (arr[m] > arr[m + 1]) {
                l = m + 1;
            } else {
                return m;
            }
        }
        return l;
    }

    @Test
    public void test() {
        int[] nums = {3, 2, 1, 0, 2, 3, 4, 6, 3, 8};
        int lessIndex = getLessIndex(nums);
        System.out.println(lessIndex);
    }

}
