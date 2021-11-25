package com.atguigu.Algorithm.Prim;

import java.util.Arrays;

public class PrimAlgorithm {
    /*
    Prim算法
    Prim算法中的贪心策略是：每次选取所有边中的最短边（权值最小边）
     */

    public static void main(String[] args) {
        //测试
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻结矩阵得关系用二维数组表示    10000表示两个点不连通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};

        //创建MGraph
        MGraph graph = new MGraph(verxs);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        //输出
        minTree.showGraph(graph);
        //测试Prim算法
        System.out.println("======================");
        System.out.println("Prim 算法");
        minTree.prim(graph, 1);
    }
}


//创建最小生成树->村庄得图
class MinTree {

    /**
     * 功能：创建图的邻接矩阵
     *
     * @param graph  图对象
     * @param verxs  图对应的顶点个数
     * @param data   图的各个顶点得值， A->0 B->1 ...
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];    //将顶点放入图
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];  //将各个结点之间的权值放入图中
            }
        }
    }

    //显示图得邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 功能：编写 Prim 算法，得到最小生成树
     *
     * @param graph 图
     * @param v     表示从图得第几个顶点开始
     */
    public void prim(MGraph graph, int v) {

        int[] visited = new int[graph.verxs];   // visited[] 标记顶点是否被访问过

        visited[v] = 1;     //把当前结点标记为已经被访问过

        //h1 h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;      //先将minWeight 初始化为一个大数
        for (int k = 1; k < graph.verxs; k++) {     //遍历结束后会有 gtaph.verxs -1 条边 所以 k 从1开始取

            //for 循环结束 即找到一条与 i 结点相连的边
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    //结点 i 访问过 结点 j 没访问过 且 i 和 j 联通（找与 i 联通的未访问过的结点中权值最小的结点 j ）
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条最小边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            //将当前结点标记为已经访问过
            visited[h2] = 1;
            //重置 minWeight
            minWeight = 10000;
        }


    }

}

//创建图类
class MGraph {
    int verxs;          //表示图得结点
    char[] data;        //存放结点数据
    int[][] weight;     //存放边，就是邻结矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}