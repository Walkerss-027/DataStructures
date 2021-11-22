package com.atguigu.DataStructures.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;   //存储顶点集合
    private int[][] edges;  //存储图对应的邻结矩阵
    private int numOfEdges; //表示边的数目
    private boolean[] isVisited;    //记录某个结点是否被访问

    public static void main(String[] args) {
        //测试
        int n = 5;
        String VertexValue[] = {"A", "B", "C", "D", "E"};

        //创建图对象
        Graph graph = new Graph(5);

        //循环添加顶点
        for (String value : VertexValue) {
            graph.inserVertex(value);
        }

        //添加边   A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);    //A-B
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        //显示邻结矩阵
        graph.showGraph();

        //测试dfs遍历
//        System.out.println("深度优先遍历");
//        graph.dfs();

        //测试bfs遍历
        System.out.println("广度优先遍历");
        graph.bfs();

    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
    }

    //深度优先遍历算法
    public void dfs(boolean[] isVisited, int i) {
        //首先访问该结点，输出
        System.out.print(getValueByIndex(i) + "->");
        //将结点设置为已经访问过
        isVisited[i] = true;
        //查找结点i 的第一个邻接结点 w
        int w = getFirstNeighbor(i);    //w 为邻接结点下标
        while (w != -1) {    //有邻接结点
            if (!isVisited[w]) {    //邻接结点没有被访问过
                dfs(isVisited, w);   //进行递归 相当于跳到下一行以 w 为头结点进行递归查找，此处w 相当于之前传进来的 i
            }
            //如果 w 结点已经被访问过
            w = getNextNeighbor(i, w);  //以第 i 行第 w 列为起点往右遍历寻找结点
        }
    }

    //对 dfs 进行重载，遍历所有节点
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        //遍历所有节点，进行dfs[回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //对一个结点进行广度优先遍历的方法
    public void bfs(boolean[] isVisited, int i) {
        int u;      //表示队列头结点对应的下标
        int w;      //邻接结点w
        LinkedList queue = new LinkedList();  //队列，记录结点访问顺序
        System.out.print(getValueByIndex(i) + "=>");    //访问节点，输出结点信息
        isVisited[i] = true;  //标记为已访问
        queue.addLast(i);   //将结点（下标）加入队列

        while (!queue.isEmpty()) {  //队列不为空
            u = (Integer) queue.removeFirst(); //取出队列头结点下标
            w = getFirstNeighbor(u);  //找到头结点的第一个邻接结点下标
            while (w != -1) {  //有邻接结点
                if (!isVisited[w]) { //邻接结点没有被访问过
                    System.out.print(getValueByIndex(w) + "=>");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w); //体现出广度优先
            }
        }
    }

    //对 bfs 进行重载，对所有结点进行广度优先查找
    public void bfs() {
        isVisited = new  boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }


    //图常用的方法


    //返回结点个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //得到第一个邻接结点的下标

    /**
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取下一个邻接结点 (实现的功能是 在 v1 行 以 v2 列为起始点往后面查询结点)
    public int getNextNeighbor(int v1, int v2) {     //v1 可以理解为行， v2 理解为列
        for (int j = v2 + 1; j < vertexList.size(); j++) {   //在 v1 行以 v2 列为起始向后遍历
            if (edges[v1][j] > 0) {
                return j;   //找到则返回列标
            }
        }
        return -1;  //没找到返回 -1
    }

    //返回结点i(下标)对应的数据  例如：0->“A”
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1,v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //插入结点
    public void inserVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边

    /**
     * @param v1     第一个顶点
     * @param v2     第二个顶点
     * @param weight 两点之间的权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

}
