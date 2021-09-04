package com.fxc.algorithm.problem.LeetCode刷题;

import org.junit.Test;

/**
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置
 *
 * @author chun
 */
public class Leetcode_045跳跃游戏II {
    public static int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int x = i + 1; x <= i + nums[i]; x++) {
                if (x >= dp.length) {
                    break;
                }
                dp[x] = dp[x] == 0 ? dp[i] + 1 : Math.min(dp[i] + 1, dp[x]);
            }
        }
        return dp[nums.length - 1];
    }

    /** 最小次数 */
    private int min = Integer.MAX_VALUE;

    /**
     * 暴力递归法(超时)
     *
     * @param nums
     * @return
     */
    public int jumpRec(int[] nums) {
        dfs(nums, 0, 0);
        return min;
    }

    public void dfs(int[] nums, int idx, int times) {
        if (idx >= nums.length - 1) {
            min = Math.min(times, min);
            return;
        }
        for (int i = 1; i <= nums[idx]; i++) {
            dfs(nums, idx + i, times + 1);
        }
    }



    /**
     * 贪心方法，每次计算当前位置能跳到的最远的位置，
     * 辅助变量保存下次新的一条的边界，达到边界开启新的跳跃，
     * 并更新下次开启新的跳跃轮次的值
     *
     * @param nums
     * @return
     */
    public int jumpTanXin(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int times = 0;
        int nextStart = 0;
        int maxPos = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            if (i == nextStart) {
                nextStart = maxPos;
                times++;
            }
        }
        return times;
    }

    @Test
    public void test() {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        int minTimes = jumpRec(nums);
        System.out.println(minTimes);
        minTimes = jumpTanXin(nums);
        System.out.println(minTimes);
    }

}
