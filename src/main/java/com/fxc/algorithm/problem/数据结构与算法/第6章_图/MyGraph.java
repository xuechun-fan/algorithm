package com.fxc.algorithm.problem.数据结构与算法.第6章_图;

import java.util.Scanner;

/**
 * 图的邻接矩阵类
 *
 * @author FXC
 */
public class MyGraph implements IGraph {

    /** 表示无穷大，两点之间没有边 */
    public static final int INFINITY = Integer.MAX_VALUE;
    /** 图的类型 */
    private GraphEnum type;
    /** 图的顶点数量 */
    private int vexNum;
    /** 图中边的数量 */
    private int arcNum;
    /** 图中的顶点 */
    private Object[] vexes;
    /** 图的邻接矩阵 */
    private int[][] arcs;

    public MyGraph(GraphEnum type, int vexNum, int arcNum, Object[] vexes, int[][] arcs) {
        this.type = type;
        this.vexNum = vexNum;
        this.arcNum = arcNum;
        this.vexes = vexes;
        this.arcs = arcs;
    }

    /*public static void main(String[] args) {
        System.out.println(GraphEnum.valueOf("UnDirectedGraph"));
        GraphEnum[] values = GraphEnum.values();
        for (GraphEnum graphEnum : values) {
            System.out.println(graphEnum);
        }
    }*/

    @Override
    public void createGraph() {

        System.out.println("please input the kind of graph. (such as DG/UDG/DN/UDN)");
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine().trim();
            if ("".equals(input)) {
                System.out.println("please check your input...");
                continue;
            }
            break;
        }
        switch (input) {
            case "DG":
                createDirectedGraph();
                return;
            case "UDG":
                createUnDirectedGraph();
                return;
            case "DN":
                createDirectedNetwork();
                return;
            case "UDN":
                createUnDirectedNetwork();
                return;
            default:
                createUnDirectedGraph();
        }
    }

    @Override
    public int getVexNum() {
        return vexNum;
    }

    @Override
    public int getArcNum() {
        return arcNum;
    }

    @Override
    public Object getVex(int v) throws Exception {
        if (v < 0 || v > vexNum) {
            throw new Exception("第" + v + "个顶点不存在!");
        }
        return vexes[v];
    }

    @Override
    public int locateVex(Object vex) {
        for (int v = 0; v < vexNum; v++) {
            if (vexes[v].equals(vex)) {
                return v;
            }
        }
        return -1;
    }

    @Override
    public int firstAdjVex(int v) throws Exception {
        if (v < 0 || v >= vexNum) {
            throw new Exception("第" + v + "个顶点不存在");
        }
        for (int i = 0; i < vexNum; i++) {
            if (arcs[v][i] != 0 && arcs[v][i] < INFINITY) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int nextAdjVex(int v, int w) throws Exception {
        if (v < 0 || v >= vexNum) {
            throw new Exception("第" + v + "个顶点不存在");
        }
        for (int i = w + 1; i < vexNum; i++) {
            if (arcs[v][i] != 0 && arcs[v][i] < INFINITY) {
                return i;
            }
        }
        return 0;
    }

    public GraphEnum getType() {
        return this.type;
    }

    public Object[] getVexes() {
        return this.vexes;
    }

    public int[][] getArcs() {
        return this.arcs;
    }

    /**
     * 创建无向图
     */
    private void createUnDirectedGraph() {
        createGraphByGraphEnum(GraphEnum.UnDirectedGraph);
    }

    /**
     * 创建有向图
     */
    private void createDirectedGraph() {
        createGraphByGraphEnum(GraphEnum.DirectedGraph);
    }

    /**
     * 创建无向网
     */
    private void createUnDirectedNetwork() {
        createGraphByGraphEnum(GraphEnum.UnDirectedNetwork);
    }

    /**
     * 创建有向网
     */
    private void createDirectedNetwork() {
        createGraphByGraphEnum(GraphEnum.DirectedNetwork);
    }

    /**
     * 通过图的类型枚举实例创建图
     *
     * @param type 图的类型枚举类实例
     */
    private void createGraphByGraphEnum(GraphEnum type) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请分别输入图的定点数、图的边数:");
        // 初始化图类数据
        this.type = type;
        vexNum = sc.nextInt();
        arcNum = sc.nextInt();
        vexes = new Object[vexNum];
        arcs = new int[vexNum][vexNum];
        System.out.println("请分别输入图的各个顶点:");
        // 构造顶点向量
        for (int i = 0; i < vexNum; i++) {
            vexes[i] = sc.nextInt();
        }
        // 标志是图还是网 true:网; 图:false;
        boolean isNetwork = type.equals(GraphEnum.DirectedNetwork) || type.equals(
                GraphEnum.UnDirectedNetwork);
        // 初始化邻接矩阵的值 网:INFINITY; 图:0;
        int initialValue = isNetwork ? INFINITY : 0;
        // 初始化邻接矩阵
        for (int i = 0; i < vexNum; i++) {
            for (int j = 0; j < vexNum; j++) {
                arcs[i][j] = initialValue;
            }
        }
        // 标志当前类型是否为有向 true:有向; false:无向;
        boolean isDirected = type.equals(GraphEnum.DirectedGraph) || type.equals(
                GraphEnum.DirectedNetwork);
        if (isNetwork) {
            // 创建的是网
            System.out.println("请输入各个边的两个顶点及其权值:");
            for (int k = 0; k < arcNum; k++) {
                int v = locateVex(sc.next());
                int u = locateVex(sc.next());
                if (isDirected) {
                    // 有向
                    arcs[v][u] = sc.nextInt();
                } else {
                    // 无向
                    arcs[v][u] = arcs[u][v] = sc.nextInt();
                }
            }

        } else {
            // 创建的是图
            System.out.println("请输入各个边的两个顶点:");
            for (int k = 0; k < arcNum; k++) {
                int v = locateVex(sc.next());
                int u = locateVex(sc.next());
                if (isDirected) {
                    // 有向
                    arcs[v][u] = 1;
                } else {
                    // 无向
                    arcs[v][u] = arcs[u][v] = 1;
                }
            }
        }
    }

}