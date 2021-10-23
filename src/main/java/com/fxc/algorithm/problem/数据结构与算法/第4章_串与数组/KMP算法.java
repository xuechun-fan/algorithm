package com.fxc.algorithm.problem.数据结构与算法.第4章_串与数组;

/**
 * KMP算法
 *
 * @author FXC
 */
public class KMP算法 {

    public static void main(String[] args) {
        KMP算法 tool = new KMP算法();
        int res = tool.match("abcabcaabcabef", "abcabd");
        System.out.println(res);
    }


    /**
     * 默认从0开始匹配
     *
     * @param str     主串
     * @param pattern 模式串
     * @return 第一次匹配成功的起始位置
     */
    public int match(String str, String pattern) {
        return match(str, pattern, 0);
    }

    /**
     * 匹配方法
     *
     * @param str     主串
     * @param pattern 模式串
     * @param start   开始匹配的起始下标
     * @return 第一次匹配成功的起始位置
     */
    public int match(String str, String pattern, int start) {

        if (pattern == null || "".equals(pattern)) {
            return -1;
        }
        int[] next = getNext(pattern);
        char[] a = str.toCharArray();
        char[] b = pattern.toCharArray();
        int i = start;
        int j = 0;
        while (i < a.length && j < b.length) {
            // j == -1 代表 a[i] != b[0]，即主串当前字符和模式串首字符不能匹配
            if (j == -1 || a[i] == b[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j < next.length) {
            return -1;
        }
        return i - j;
    }


    /**
     * 根据模式串获取next数组，next数组保存了前缀子串的信息
     *
     * @param pattern 输入模式串
     * @return 返回next数组
     */
    private int[] getNext(String pattern) {
        char[] t = pattern.toCharArray();
        int[] next = new int[t.length];
        next[0] = -1;
        next[1] = 0;
        int j = 1;
        int k = 0;
        while (j < next.length - 1) {
            if (t[j] == t[k]) {
                next[j + 1] = k + 1;
                j++;
                k++;
            } else if (k == 0) {
                next[j + 1] = 0;
                j++;
            } else {
                k = next[k];
            }
        }
        return next;
    }

}