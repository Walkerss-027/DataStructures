package com.atguigu.Algorithm.Floyd;

import java.util.Arrays;

public class FloydAlgorithm {
    /*
    floyd算法
    应用场景：最短路径
    关键特征：
     */
    public static void main(String[] args) {
        //测试
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        //创建 Graph 对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show();

    }

}


//创建图
class Graph {
    private char[] vertex;  //存放顶点的数组
    private int[][] dis;    //保存从各个顶点出发到其他顶点的距离，最后的结果也保留在该数组
    private int[][] pre;    //保存到达目标顶点的前驱顶点

    /**
     * 功能：构造器
     *
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];

        //对pre 数组初始化，存放的前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    //显示 pre 数组和 dis 数组
    public void show() {

        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        for (int k = 0; k < dis.length; k++) {
            //输出 pre 数组
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            //输出 dis 数组
            for (int i = 0; i < dis.length; i++) {
                System.out.print("("+vertex[k]+"到"+vertex[i]+"的最短路径是" + dis[k][i] + ") ");
            }
            System.out.println();
            System.out.println();
        }
    }

    //Floyd算法
    public void floyd() {
        int len = 0;

        //对 k 中间顶点的遍历
        for (int k = 0; k < dis.length; k++) {

            //从i 顶点出发 {'A', 'B', 'C', 'D', 'E', 'F', 'G'}
            for (int i = 0; i < dis.length; i++) {

                //到达 j 顶点 {'A', 'B', 'C', 'D', 'E', 'F', 'G'}
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];    //求出以 i 为出发点，经过 k 中间节点，到达 j 的距离
                    if (len < dis[i][j]) {
                        dis[i][j] = len;
                        pre[i][j]=pre[k][j];    //更新前驱结点
                    }
                }
            }
        }
    }
}
