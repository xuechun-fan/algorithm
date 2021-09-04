package com.fxc.algorithm.problem.LeetCode刷题;

/**
 * 最长回文子串
 *
 * @author chun
 */
public class Leetcode_005_最长回文子串 {
    /**
     * 方法1：动态规划法
     *
     * @param s 输入字符串
     * @return 最长回文子串长度
     */
    public static String longestPalindromeDp(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        //  从第一个字符开始遍历
        for (int l = 0; l < n; l++) {
            //  查找最长回文子串
            for (int i = 0; i + l < n; i++) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else {
                    boolean b = s.charAt(i) == s.charAt(j);
                    if (l == 1) {
                        dp[i][j] = b;
                    } else {
                        dp[i][j] = (b && dp[i + 1][j - 1]);
                    }
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }

    /**
     * 方法2：中心扩展法
     *
     * @param s 输入字符串
     * @return 最长回文子串长度
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len >= end - start + 1) {
                start = i - ((len - 1) >> 1);
                end = i + (len >> 1);
            }
        }
        return s.substring(start, end + 1);
    }

    public static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        String s = "abbbacd";
        String ans = longestPalindrome(s);
        System.out.println(ans);
    }
}
