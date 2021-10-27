package com.fxc.algorithm.problem.数据结构与算法.第6章_图;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的遍历相关算法
 * 1、广度优先遍历
 * 2、深度优先遍历
 *
 * @author FXC
 */
public class 图的遍历相关算法 {

    /** 标志图中对应节点是否被访问过 */
    private static boolean[] visited;

    public static void bfsTraverse(IGraph graph) throws Exception {
        int vexNum = graph.getVexNum();
        visited = new boolean[vexNum];
        for (int v = 0; v < graph.getVexNum(); v++) {
            if (!visited[v]) {
                // 从当前节点开始广度遍历
                bfsCore(graph, v);
            }
        }
    }

    /**
     * 广度遍历核心代码
     * 思路类似于二叉树的层序遍历，利用栈来保证遍历是按照层来进行的
     *
     * @param graph 要开始遍历的图节点
     * @param v     当前节点下标
     * @throws Exception 抛出异常
     */
    private static void bfsCore(IGraph graph, int v) throws Exception {
        visited[v] = true;
        System.out.println(graph.getVex(v).toString() + " ");
        Queue<Object> queue = new LinkedList<>();
        queue.offer(v);
        while (!queue.isEmpty()) {
            int u = (Integer) queue.poll();
            for (int w = graph.firstAdjVex(u); w >= 0; w = graph.nextAdjVex(u, w)) {
                if (!visited[w]) {
                    visited[w] = true;
                    System.out.println(graph.getVex(w).toString() + " ");
                    queue.offer(graph.getVex(w));
                }
            }

        }
    }

    public static void dfsTraverse(IGraph graph) throws Exception {
        int vexNum = graph.getVexNum();
        visited = new boolean[vexNum];
        for (int v = 0; v < vexNum; v++) {
            if (!visited[v]) {
                dfsCore(graph, v);
            }
        }
    }

    /**
     * 深度优先遍历核心代码
     * 从第v个顶点出发递归地深度优先搜索遍历图graph
     *
     * @param graph 要遍历的图
     * @param v     当前节点下标
     * @throws Exception 抛出异常
     */
    private static void dfsCore(IGraph graph, int v) throws Exception {
        visited[v] = true;
        System.out.println(graph.getVex(v).toString() + " ");
        for (int w = graph.firstAdjVex(v); w >= 0; w = graph.nextAdjVex(v, w)) {
            if (!visited[w]) {
                dfsCore(graph, w);
            }
        }
    }


}