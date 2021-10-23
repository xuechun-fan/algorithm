package com.fxc.algorithm.problem.LeetCode刷题;

import com.fxc.algorithm.problem.util.TreeNode;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 *
 * @author FXC
 */
public class Leetcode_230_BST中第K小的元素 {
    /** 输入参数k：第k小 */
    private int k;
    /** 保存结果的变量 */
    private int res;

    /**
     * 查找二叉搜索树中第 k 个最小元素
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        inorder(root);
        return res;
    }

    /**
     * 中序遍历模板改造
     *
     * @param root
     */
    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        // TODO: 2021/10/17  
        k--;
        if (k == 0) {
            res = root.val;
            return;
        }
        inorder(root.right);
    }
}