package com.fxc.algorithm.problem.数据结构与算法.第6章_图;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图相关算法应用
 * 1、利用广度优先遍历确定无向图的连通分量
 * 2、判断有向图中的u、v两顶点之间是否存在长度为k的路径
 *
 * @author FXC
 */
public class 图相关算法应用 {

    // 1、利用广度优先遍历确定无向图的连通分量
    /** 表示无穷大，两点之间没有边 */
    public static final int INFINITY = Integer.MAX_VALUE;

    // 2、判断有向图中的u、v两顶点之间是否存在长度为k的路径
    /** 访问标志数组 */
    private boolean[] visited;
    /** 辅助变量，在遍历过程中用于记录从起点出发的路径长度 */
    private int i = 0;
    /** 标识是否已经找到了指定长度的路径 */
    private boolean find = false;

    /**
     * test1 邻接矩阵形式的图，测试求非连通图中的连通分量
     *
     * @throws Exception
     */
    @Test
    public void testCC_BFS() throws Exception {
        Object[] vexes = {"A", "B", "C", "D", "E", "F", "G"};
        int[][] arcs = {{0, 1, INFINITY, 1, INFINITY, INFINITY, INFINITY}, {1, 0, 1, INFINITY,
                INFINITY, INFINITY, INFINITY}, {INFINITY, 1, 0, 1, INFINITY, INFINITY, INFINITY},
                {1, INFINITY, 1, 0, INFINITY, INFINITY, INFINITY}, {INFINITY, INFINITY, INFINITY,
                INFINITY, 0, 1, INFINITY}, {INFINITY, INFINITY, INFINITY, INFINITY, 1, 0, 1},
                {INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, 1, 0}};
        MatrixGraph graph = new MatrixGraph(GraphEnum.UnDirectedGraph, 7, 6, vexes, arcs);
        CC_BFS(graph);
    }

    /**
     * 利用广度优先遍历确定无向图的连通分量
     *
     * @param graph
     * @throws Exception
     */
    public void CC_BFS(IGraph graph) throws Exception {
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

    @Test
    public void testFindPath() throws Exception {
        // 初始化边节点
        ArcNode ab = new ArcNode(1);
        ArcNode be = new ArcNode(4);
        ArcNode bc = new ArcNode(2, 0, be);
        ArcNode cd = new ArcNode(3);
        ArcNode de = new ArcNode(4);
        ArcNode ef = new ArcNode(5);
        ArcNode fa = new ArcNode(0);
        ArcNode fb = new ArcNode(1, 0, fa);

        // 初始化顶点
        VNode a = new VNode("A", ab);
        VNode b = new VNode("B", bc);
        VNode c = new VNode("C", cd);
        VNode d = new VNode("D", de);
        VNode e = new VNode("E", ef);
        VNode f = new VNode("F", fb);

        // 初始化图
        VNode[] vexes = {a, b, c, d, e, f};
        ALGraph graph = new ALGraph(GraphEnum.DirectedGraph, 6, 8, vexes);

        findPath(graph, 0, 5, 5);
    }

    /**
     * 判断有向图中的u、v两顶点之间是否存在长度为k的路径
     *
     * @param graph 有向图
     * @param u     u顶点下标
     * @param v     v顶点下标
     * @param k     目标长度
     * @throws Exception 抛出异常
     */
    public void findPath(IGraph graph, int u, int v, int k) throws Exception {
        visited = new boolean[graph.getVexNum()];
        Arrays.fill(visited, false);
        findDfs(graph, u, v, k);
        if (find) {
            System.out.println(
                    graph.getVex(u) + "和" + graph.getVex(v) + "之间 (存在) 一条长度为 " + k + " 的简单路径");
        } else {
            System.out.println(
                    graph.getVex(u) + "和" + graph.getVex(v) + "之间 (不存在) 一条长度为 " + k + " 的简单路径");
        }

    }

    /**
     * 深度搜索路径核心代码
     *
     * @param graph 有向图
     * @param u     u顶点下标
     * @param v     v顶点下标
     * @param k     目标长度
     * @throws Exception 抛出异常
     */
    private void findDfs(IGraph graph, int u, int v, int k) throws Exception {
        if (i == k && u == v) {
            find = true;
        } else if (!find) {
            visited[u] = true;
            for (int w = graph.firstAdjVex(u); w >= 0; w = graph.nextAdjVex(u, w)) {
                if (!visited[w]) {
                    if (i < k) {
                        // 未达到期望路径长度，则继续累加并递归搜索
                        ++i;
                        // 对v的尚未访问的邻接顶点w递归调用findDfs
                        findDfs(graph, w, v, k);
                    } else {
                        // 若路径长度已达到k值而仍未找到简单路径，则不再继续对当前顶点进行深度优先搜索
                        break;
                    }
                }
            }
            // 回退一个顶点
            --i;
        }
    }

}