package com.fxc.algorithm.problem.数据结构与算法.第6章_图;

/**
 * 图抽象数据类型
 *
 * @author FXC
 */
public interface IGraph {

    /**
     * 根据传入类型创建相应的图
     */
    void createGraph();

    /**
     * 获取图中顶点个数
     *
     * @return 图中顶点个数
     */
    int getVexNum();

    /**
     * 获取图中边的数量
     *
     * @return 图中边的数量
     */
    int getArcNum();

    /**
     * 根据图中顶点的位置获取对应的顶点
     *
     * @param v 图中顶点的位置
     * @return 对应的顶点, 0 <= v < vexNum
     * @throws Exception 若v范围非法则抛出异常
     */
    Object getVex(int v) throws Exception;

    /**
     * 获取vex顶点在图中的位置
     *
     * @param vex 要被查找的顶点
     * @return vex在图中的位置; -1: 图中不存在vex;
     */
    int locateVex(Object vex);

    /**
     * 返回v的第一个邻接点的位置
     *
     * @param v 当前顶点的位置
     * @return v的第一个邻接点的位置; -1: v没有邻接点;
     */
    int firstAdjVex(int v);

    /**
     * 返回v相对于w的下一个邻接点的位置，若w是v的最后一个邻接点，则返回-1
     *
     * @param v v
     * @param w w
     * @return -1: w是v的最后一个邻接点; v相对于w的下一个邻接点位置
     */
    int nextAdjVex(int v, int w);
}
