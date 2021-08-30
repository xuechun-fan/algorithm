package com.fxc.algorithm.problem.LeetCode刷题;


import java.util.HashMap;
import java.util.Map;

/**
 * 题目 leetcode 454
 *
 * @author chun
 */
public class Leetcode_454_四数相加II {

    /**
     * 给定四个包含整数的数组列表A , B , C , D ,计算有多少个元组 (i, j, k, l)，
     * 使得A[i] + B[j] + C[k] + D[l] = 0。
     * <p>
     * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度N，且 0 ≤ N ≤ 500 。
     * 所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 2^31 - 1
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>(16);
        int n = nums1.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = nums1[i] + nums2[j];
                if (!map.containsKey(sum)) {
                    map.put(sum, 1);
                } else {
                    map.put(sum, map.get(sum) + 1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = nums3[i] + nums4[j];
                if (map.containsKey(-sum)) {
                    ans += map.get(-sum);
                }
            }
        }

        return ans;

    }
}
