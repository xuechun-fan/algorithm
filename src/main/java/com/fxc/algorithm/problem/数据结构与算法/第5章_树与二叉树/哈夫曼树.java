package com.fxc.algorithm.problem.数据结构与算法.第5章_树与二叉树;

/**
 * 哈夫曼树及哈夫曼编码
 *
 * @author FXC
 */
public class 哈夫曼树 {

    public static void main(String[] args) {
        // 对应 a b c d e f g h 字符
        int[] w = {6, 30, 8, 9, 15, 24, 4, 12};
        HuffmanTree huffmanTree = new HuffmanTree();
        int[][] huffCodes = huffmanTree.huffmanCoding(w);
        boolean start = false;
        int i = 0;
        for (int[] line : huffCodes) {
            System.out.print((char) ('a' + i++) + ":\t");
            for (int code : line) {
                if (code == -1) {
                    start = true;
                    continue;
                }
                if (start) {
                    System.out.print(code + "\t");
                }
            }
            start = false;
            System.out.println();
        }
    }


}

/**
 * 哈夫曼树节点
 *
 * @author FXC
 */
class HuffmanNode {

    /** 字符 */
    private char ch;
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

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
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
        // 根据权值数组构建出n个哈夫曼节点
        for (i = 0; i < n; i++) {
            huffmanNodes[i] = new HuffmanNode(w[i]);
            huffmanNodes[i].setCh((char) ('a' + i));
        }

        // 建哈夫曼树
        for (i = n; i < m; i++) {
            HuffmanNode min1 = selectMin(huffmanNodes, i - 1);
            assert min1 != null;
            min1.setFlag(1);
            HuffmanNode min2 = selectMin(huffmanNodes, i - 1);
            assert min2 != null;
            min2.setFlag(1);

            // 构建min1和min2的父节点
            HuffmanNode parent = new HuffmanNode(min1.getWeight() + min2.getWeight());
            parent.setLeft(min1);
            parent.setRight(min2);
            min1.setParent(parent);
            min2.setParent(parent);
            huffmanNodes[i] = parent;
        }

        // 从叶节点到根逆向求每个字符的哈夫曼编码
        int[][] huffCode = new int[n][n];
        for (int j = 0; j < n; j++) {
            int start = n - 1;
            for (HuffmanNode c = huffmanNodes[j], p = c.getParent(); p != null; c = p, p =
                    p.getParent()) {
                if (p.getLeft().equals(c)) {
                    huffCode[j][start--] = 0;
                } else {
                    huffCode[j][start--] = 1;
                }
            }
            huffCode[j][start] = -1;
        }
        return huffCode;
    }

    /**
     * 从节点数组中选出不在哈夫曼树中最小的节点
     *
     * @param huffmanNodes 节点数组
     * @param end          搜索范围的最后一个下标边界
     * @return 符合条件的哈夫曼节点
     */
    private HuffmanNode selectMin(HuffmanNode[] huffmanNodes, int end) {
        if (end < 0 || end >= huffmanNodes.length) {
            return null;
        }
        HuffmanNode min = huffmanNodes[end];
        for (int i = 0; i <= end; i++) {
            if (huffmanNodes[i].getFlag() == 0 && huffmanNodes[i].getWeight() < min.getWeight()) {
                min = huffmanNodes[i];
            }
        }
        return min;
    }
}