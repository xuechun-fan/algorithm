package com.fxc.algorithm.problem.数据结构与算法.第5章_树与二叉树;

import com.fxc.algorithm.problem.util.TreeNode;

import java.util.Stack;

/**
 * 二叉树遍历算法
 *
 * @author FXC
 */
public class 二叉树遍历算法 {

    /**
     * 前序遍历非递归版
     *
     * @param root 树的根节点
     */
    public void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            printNode(tmp);
            while (tmp != null) {
                if (tmp.left != null) {
                    printNode(tmp.left);
                }
                if (tmp.right != null) {
                    stack.push(tmp.right);
                }
                tmp = tmp.left;
            }
        }
    }


    /**
     * 打印当前节点的值
     *
     * @param root 当前节点
     */
    private void printNode(TreeNode root) {
        System.out.println(root.val);
    }

}