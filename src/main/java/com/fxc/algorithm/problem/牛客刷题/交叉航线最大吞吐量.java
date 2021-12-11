package com.fxc.algorithm.problem.牛客刷题;

import java.util.*;

/**
 * 交叉航线最大吞吐量
 *
 * @author FXC
 */
public class 交叉航线最大吞吐量 {

    public static void main(String[] args) {
        System.out.println(maxThroughput());
    }

    /**
     * 输入 n + 1 行数据
     * 第一行输入一个数字 n，代表有n条航线
     * 接下来n行每行输入三个数字，分别为南岸编号、北岸编号、该航线吞吐量
     *
     * 测试：
     * 3
     * 1 1 0
     * 2 5 2
     * 3 2 3
     *  == > 3
     *
     *  5
     *  2 5 3
     *  1 4 2
     *  4 2 2
     *  3 1 1
     *  5 3 1
     *   == > 5
     *
     *  5
     *  1 2 1
     *  2 3 2
     *  3 1 4
     *  4 4 3
     *  5 5 5
     *   == > 12
     *
     * @return 返回删除某些交叉航线后能够获得非交叉航线的最大吞吐量
     */
    public static int maxThroughput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字n：");
        int n = sc.nextInt();
        System.out.println("请依次输入n行数据：");
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
        }
        // 将nums数组按照0列排序
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        // 排序后抽取出北岸编号数据，依次确定递增子序列
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = arr[i][1];
        }

        // 当前最长递增子序列的长度
        int idx = 0;
        // 最大吞吐量
        int max = 0;
        // 中间变量，用来帮助确定递增子序列
        int[] res = new int[n];
        // 保存对应长度的子序列的吞吐量
        int[] maxT = new int[n];
        Map<Integer, Integer> dic = new HashMap<>();
        // 确定子序列
        for (int i = 0; i < n; i++) {
            int w = arr[i][2];
            // 利用二分思想快速确定当前数字nums[i]应该在res数组中的位置
            int l = 0;
            int r = idx;
            while (l < r) {
                int m = l + ((r - l) >> 1);
                if (res[m] < nums[i]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            res[l] = nums[i];

            maxT[l] = l == 0 ? w : maxT[l - 1] + w;

            if (l == 0) {
                dic.put(nums[i], w);
            } else {
                // 更新dic
                for (int key : dic.keySet()) {
                    if (key < nums[i]) {
                        dic.replace(key, Math.max(dic.get(key) + (l == idx ? w : 0), maxT[l]));
                    }
                }
            }
            // 判断最长递增子序列长度是否有变化
            if (l == idx) {
                // 长度变化
                idx++;
            }
        }
        // 找最大的吞吐量
        for (int value : dic.values()) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}