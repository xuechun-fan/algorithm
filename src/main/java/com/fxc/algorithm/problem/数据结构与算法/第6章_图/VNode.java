package com.fxc.algorithm.problem.数据结构与算法.第6章_图;

/**
 * 图的邻接表存储表示中的顶点节点类
 *
 * @author FXC
 */
public class VNode {

    /** 顶点信息 */
    private Object data;
    /** 指向第一条依附于当前顶点的边 */
    private ArcNode firstArc;

    public VNode() {
        this(null, null);
    }

    public VNode(Object data) {
        this.data = data;
        this.firstArc = null;
    }

    public VNode(Object data, ArcNode firstArc) {
        this.data = data;
        this.firstArc = firstArc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ArcNode getFirstArc() {
        return firstArc;
    }

    public void setFirstArc(ArcNode firstArc) {
        this.firstArc = firstArc;
    }
}