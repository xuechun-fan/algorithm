package com.fxc.algorithm.problem.数据结构与算法.第5章_树与二叉树;

/**
 * 哈夫曼树及哈夫曼编码
 *
 * @author FXC
 */
public class 哈夫曼树 {


}

/**
 * 哈夫曼树节点
 *
 * @author FXC
 */
class HuffmanNode {

    /** 权重 */
    private int weight;
    /** 结点是否加入哈夫曼树的标志 */
    private int flag;
    /** 父节点 */
    private HuffmanNode parent;
    /** 左孩子 */
    private HuffmanNode left;
    /** 右孩子 */
    private HuffmanNode right;

    public HuffmanNode() {
    }

    public HuffmanNode(int weight) {
        this.weight = weight;
        this.flag = 0;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public HuffmanNode getParent() {
        return parent;
    }

    public void setParent(HuffmanNode parent) {
        this.parent = parent;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }
}

/**
 * 哈夫曼树
 *
 * @author FXC
 */
class HuffmanTree {

    /**
     * 求哈夫曼编码
     *
     * @param w 存放N个字符的权值(权值大于0)
     * @return 哈夫曼编码数组
     */
    public int[][] huffmanCoding(int[] w) {
        int n = w.length;
        // 哈夫曼树的节点个数
        int m = 2 * n - 1;
        HuffmanNode[] huffmanNodes = new HuffmanNode[m];
        int i;
        for (i = 0; i < m; i++) {
            huffmanNodes[i] = new HuffmanNode(w[i]);
        }

        // 建哈夫曼树
        for (i = n; i < m; i++) {

        }
        return null;
    }

    private HuffmanNode selectMin(HuffmanNode[] huffmanNodes, int end) {
        HuffmanNode min = huffmanNodes[end];

        return min;
    }
}