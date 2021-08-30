package com.fxc.algorithm.problem.LeetCode刷题;


/**
 * 字符串转换整数（aoti)
 *
 * @author chun
 */
public class Leetcode_008_字符串转换整数 {
    public static int myAoti(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int index = 0;
        while (index < len && s.charAt(index) == ' ') {
            index++;
        }
        if (index == len) {
            return 0;
        }

        int sign = 1;
        char firstChar = s.charAt(index);
        if (firstChar == '+') {
            index++;
        } else if (firstChar == '-') {
            sign = -1;
            index++;
        }

        int res = 0;
        while (index < len) {
            char curChar = s.charAt(index);
            if (curChar > '9' || curChar < '0') {
                break;
            }

            //  判断是否越界
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (curChar - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (curChar - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            res = res * 10 + sign * (curChar - '0');
            index++;
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        String s = "-2147483647";
        int ans = myAoti(s);
        System.out.println(ans);
        System.out.println(-(Integer.MIN_VALUE % 10));
    }
}
