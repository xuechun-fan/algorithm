package com.fxc.algorithm.problem.数据结构与算法.第6章_图;

/**
 * 边节点类
 *
 * @author FXC
 */
public class ArcNode {
    /** 该弧指向的顶点的位置 */
    private int adjVex;
    /** 边的权值 */
    private int weight;
    /** 指向下一条弧 */
    private ArcNode nextArc;

    public ArcNode() {
        this(-1, 0, null);
    }

    public ArcNode(int adjVex) {
        this(adjVex, 0, null);
    }

    public ArcNode(int adjVex, int weight) {
        this(adjVex, weight, null);
    }

    public ArcNode(int adjVex, int weight, ArcNode nextArc) {
        this.adjVex = adjVex;
        this.weight = weight;
        this.nextArc = nextArc;
    }

    public int getAdjVex() {
        return adjVex;
    }

    public void setAdjVex(int adjVex) {
        this.adjVex = adjVex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ArcNode getNextArc() {
        return nextArc;
    }

    public void setNextArc(ArcNode nextArc) {
        this.nextArc = nextArc;
    }
}