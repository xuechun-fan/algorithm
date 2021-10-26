package com.fxc.algorithm.problem.数据结构与算法.第6章_图;

import java.util.Scanner;

/**
 * 图的邻接表类
 *
 * @author FXC
 */
public class ALGraph implements IGraph {

    /** 图的类型 */
    private GraphEnum type;
    /** 图的顶点数量 */
    private int vexNum;
    /** 图中边的数量 */
    private int arcNum;
    /** 图中的顶点 */
    private VNode[] vexes;

    public ALGraph() {
        this(null, 0, 0, null);
    }

    public ALGraph(GraphEnum type) {
        this(type, 0, 0, null);
    }

    public ALGraph(GraphEnum type, int vexNum) {
        this(type, vexNum, 0, null);
    }

    public ALGraph(GraphEnum type, int vexNum, int arcNum) {
        this(type, vexNum, arcNum, null);
    }

    public ALGraph(GraphEnum type, int vexNum, int arcNum, VNode[] vexes) {
        this.type = type;
        this.vexNum = vexNum;
        this.arcNum = arcNum;
        this.vexes = vexes;
    }

    public static void main(String[] args) {
        ALGraph tool = new ALGraph();
        tool.createGraph();
    }

    @Override
    public void createGraph() {
        System.out.println(
                "请输入要创建图的类型. \n(例: \n\tDG = 有向图\n" + "\tUDG = 无向图\n" + "\tDN = 有向网\n" + "\tUDN = "
                        + "无向网)\n");
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

    /**
     * 在v顶点和u顶点之间添加边, 边的方向从v指向u
     * (采用头插法)
     *
     * @param v     v顶点下标
     * @param u     u顶点下标
     * @param value 边的权值
     */
    public void addArc(int v, int u, int value) {
        // 头插法
        ArcNode newNode = new ArcNode(u, value);
        newNode.setNextArc(vexes[v].getFirstArc());
        vexes[v].setFirstArc(newNode);
    }

    @Override
    public int getVexNum() {
        return this.vexNum;
    }

    @Override
    public int getArcNum() {
        return this.arcNum;
    }

    @Override
    public Object getVex(int v) throws Exception {
        if (v < 0 || v >= vexNum) {
            throw new Exception("第" + v + "个顶点不存在!");
        }
        return vexes[v].getData();
    }

    @Override
    public int locateVex(Object vex) {
        for (int v = 0; v < vexNum; v++) {
            if (vexes[v].getData().equals(vex)) {
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
        VNode vex = vexes[v];
        if (vex.getFirstArc() != null) {
            return vex.getFirstArc().getAdjVex();
        }
        return -1;
    }

    @Override
    public int nextAdjVex(int v, int w) throws Exception {
        if (v < 0 || v >= vexNum) {
            throw new Exception("第" + v + "个顶点不存在");
        }
        VNode vex = vexes[v];
        ArcNode arcVW = null;
        for (ArcNode arc = vex.getFirstArc(); arc != null; arc = arc.getNextArc()) {
            if (arc.getAdjVex() == w) {
                arcVW = arc;
                break;
            }
        }
        if (arcVW != null && arcVW.getNextArc() != null) {
            return arcVW.getNextArc().getAdjVex();
        }
        return -1;
    }

    public GraphEnum getType() {
        return type;
    }

    public VNode[] getVexes() {
        return vexes;
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
        this.vexNum = sc.nextInt();
        this.arcNum = sc.nextInt();
        this.vexes = new VNode[vexNum];
        System.out.println("请分别输入图的各个顶点:");
        // 构造顶点向量
        for (int i = 0; i < vexNum; i++) {
            vexes[i] = new VNode(sc.nextInt());
        }
        // 标志是图还是网 true:网; 图:false;
        boolean isNetwork = type.equals(GraphEnum.DirectedNetwork) || type.equals(
                GraphEnum.UnDirectedNetwork);
        // 标志当前类型是否为有向 true:有向; false:无向;
        boolean isDirected = type.equals(GraphEnum.DirectedGraph) || type.equals(
                GraphEnum.DirectedNetwork);
        if (isNetwork) {
            // 创建的是网
            System.out.println("请输入各个边的两个顶点及其权值:");
            for (int k = 0; k < arcNum; k++) {
                int v = locateVex(sc.next());
                int u = locateVex(sc.next());
                int value = sc.nextInt();
                addArc(v, u, value);
                if (!isDirected) {
                    // 无向
                    addArc(u, v, value);
                }
            }
        } else {
            // 创建的是图
            System.out.println("请输入各个边的两个顶点:");
            for (int k = 0; k < arcNum; k++) {
                int v = locateVex(sc.next());
                int u = locateVex(sc.next());
                addArc(v, u, 1);
                if (!isDirected) {
                    // 无向
                    addArc(u, v, 1);
                }
            }
        }

        System.out.println("图数据结构创建完毕...");
    }
}