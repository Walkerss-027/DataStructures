package com.atguigu.Algorithm.Kruskal;

import java.util.Arrays;

public class KruscalCase {
    /*
    克鲁斯卡尔算法
    与Prim算法类似，克鲁斯卡尔算法也是一种贪心算法，它的贪心策略是每次选权值最小边。
     */

    private int edgeNum;        //边的个数
    private char[] vertexs;     //顶点数组
    private int[][] matrix;     //邻结矩阵
    private static final int INF = Integer.MAX_VALUE;     //使用 INF 表示两点不连通

    public static void main(String[] args) {

        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //创建 Kruscal 邻接矩阵
        int matrix[][] = {
                {  0,  12, INF, INF, INF,  16,  14},
                { 12,   0,  10, INF, INF,   7, INF},
                {INF,  10,   0,   3,   5,   6, INF},
                {INF, INF,   3,   0,   4, INF, INF},
                {INF, INF,   5,   4,   0,   2,   8},
                { 16,   7,   6, INF,   2,   0,   9},
                { 14, INF, INF, INF,   8,   9,   0}};

        //创建KruscalCase 对象示例
        KruscalCase kruscalCase = new KruscalCase(vertexs, matrix);
        //输出构建的示例
        kruscalCase.print();
        kruscalCase.kruskal();

//        EData[] edges = kruscalCase.getEdges();
//        System.out.println(Arrays.toString(edges));
//        kruscalCase.sortEnges(edges);
//        System.out.println(Arrays.toString(edges));

    }


    //构造器
    public KruscalCase(char[] vertexs, int[][] matrix) {
        int vlen = vertexs.length;

        //采用复制拷贝(深拷贝)的方式初始化顶点
        this.vertexs = new char[vlen];
        for (int i = 0; i < vlen; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //采用复制拷贝(深拷贝)的方式初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //统计边(所有边)
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }

    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵:\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%-12d", matrix[i][j]);       // 12表示12个占位符，-表示左对齐
            }
            System.out.println();   //换行
        }
    }

    //根据边的权值进行排序，冒泡
    private void sortEnges(EData[] edges) {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    //返回顶点的值（对应的下标），如果找不到返回-1
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    //通过 matrix 邻接矩阵，获取图中的边，放到EData[]数组中，后面需要遍历该数组
    //EData[]形式 ['A','B',12],['B','F',7]...
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能：获取下标为 i 顶点的终点,用于判断两个顶点的终点是否相同
     *
     * @param ends 记录各个顶点对应的终点，end数组在遍历过程中逐步形成
     * @param i    传入的顶点对应的下标
     * @return 返回的就是下标为i的顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    public void kruskal() {
        int index = 0;   //表示最后结果数组的索引
        int[] ends = new int[edgeNum];    //用于保存“已有最小生成树”中的每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        //获取所有边的集合,一共12条边
        EData[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + "共" + edges.length);  //12

        //按照边的权值大小进行排序（从小到大）
        sortEnges(edges);

        //遍历 edges 数组，将边添加到最小生成树中时，判断准备加入的边是否形成回路（难点）
        for (int i = 0; i < edgeNum; i++) {
            //获取第 i 条边的第一个顶点
            int p1 = getPosition(edges[i].start);
            //获取第 i 条边的第二个顶点
            int p2 = getPosition(edges[i].end);

            //获取 p1 这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            //获取 p2 这个顶点在已有最小生成树中的终点
            int n = getEnd(ends, p2);
            //是否构成回路
            if (m != n) {   //没有构成回路
                ends[m] = n;    // 设置 m 在已有最新小生成树中的终点
                rets[index++] = edges[i];     //有一条边加入到 rets 数组
            }
        }

        //统计并打印最小生成树，输出 rets
        for (int i=0;i<index;i++) {
            System.out.println(rets[i]);
        }

    }


}


//创建一个类EData ， 它的对象实例就表示一条边
class EData {
    char start;     //边的起点
    char end;       //边的重点
    int weight;     //边的权值

    //构造器
    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //重写 toString
    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                ", " + end +
                "> =" + weight +
                '}';
    }
}
