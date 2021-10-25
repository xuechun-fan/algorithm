package com.fxc.algorithm.problem.数据结构与算法.第6章_图;

/**
 * 图的类型枚举类
 * 图分为无向图、有向图、无向网、有向网(边带了权值的就称为网)
 *
 * @author FXC
 */
public enum GraphEnum {
    /** 无向图 */
    UnDirectedGraph,
    /** 有向图 */
    DirectedGraph,
    /** 无向网 */
    UnDirectedNetwork,
    /** 有向网 */
    DirectedNetwork;


    @Override
    public String toString() {
        return super.toString();
    }
}
