package com.fxc.algorithm.problem.数据结构与算法.第6章_图;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 利用广度优先遍历确定无向图的连通分量
 *
 * @author FXC
 */
public class 利用广度优先遍历确定无向图的连通分量 {

    /** 表示无穷大，两点之间没有边 */
    public static final int INFINITY = Integer.MAX_VALUE;

    /**
     * 利用广度优先遍历确定无向图的连通分量
     *
     * @param graph
     * @throws Exception
     */
    public static void CC_BFS(IGraph graph) throws Exception {
        int vexNum = graph.getVexNum();
        boolean[] visited = new boolean[vexNum];
        // 辅助队列，用来实现BFS
        Queue<Object> q = new LinkedList<>();
        // 辅助队列，用来保存当前的子连通分量
        Queue<Object> subC = new LinkedList<>();
        int i = 0;
        for (int v = 0; v < graph.getVexNum(); v++) {
            // 清空子连通分量的队列
            subC.clear();
            if (!visited[v]) {
                visited[v] = true;
                subC.offer(graph.getVex(v));
                q.offer(v);
                while (!q.isEmpty()) {
                    int u = (Integer) q.poll();
                    for (int w = graph.firstAdjVex(u); w >= 0; w = graph.nextAdjVex(u, w)) {
                        if (!visited[w]) {
                            visited[w] = true;
                            subC.offer(graph.getVex(w));
                            q.offer(w);
                        }
                    }
                }
                System.out.println("图的第" + ++i + "个连通分量为：");
                while (!subC.isEmpty()) {
                    System.out.print(subC.poll().toString() + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Object[] vexes = {"A", "B", "C", "D", "E", "F", "G"};
        int[][] arcs = {
                {0, 1, INFINITY, 1, INFINITY, INFINITY, INFINITY},
                {1, 0, 1, INFINITY, INFINITY, INFINITY, INFINITY},
                {INFINITY, 1, 0, 1, INFINITY, INFINITY, INFINITY},
                {1, INFINITY, 1, 0, INFINITY, INFINITY, INFINITY},
                {INFINITY, INFINITY, INFINITY, INFINITY, 0, 1, INFINITY},
                {INFINITY, INFINITY, INFINITY, INFINITY, 1, 0, 1},
                {INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, 1, 0}
        };
        MatrixGraph graph = new MatrixGraph(GraphEnum.UnDirectedGraph, 7, 6, vexes, arcs);
        CC_BFS(graph);
    }

}