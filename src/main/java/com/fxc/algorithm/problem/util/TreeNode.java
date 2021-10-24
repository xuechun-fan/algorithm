package com.fxc.algorithm.problem.util;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int val, TreeNode left) {
        this.val = val;
        this.left = left;
    }

    public TreeNode(int val, int a, int b) {
        this.val = val;
        this.left = new TreeNode(a);
        this.right = new TreeNode(b);
    }

    public TreeNode(int val, int left, TreeNode right) {
        this.val = val;
        this.left = new TreeNode(left);
        this.right = right;
    }

    public TreeNode(int val, TreeNode left, int right) {
        this.val = val;
        this.left = left;
        this.right = new TreeNode(right);
    }

    public TreeNode(int val) {
        this.val = val;
    }
}
