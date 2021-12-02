package com.atguigu.Algorithm.Dijkstra;

import java.rmi.dgc.VMID;
import java.util.Arrays;

public class DijkstraAlgorithm {
    /*
    Dijkstra 算法
    贪心算法 + 广度优先
    适用场景： 最短路径 公交问题
    关键特征：
     */

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        //创建 Graph 对象
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dsj(2);
        graph.showDijkstra();

    }

}


//创建图类
class Graph {
    private char[] vertex;      //顶点数组
    private int[][] matrix;     //邻接矩阵
    private VisitedVertex vv;   //已经访问的顶点的集合

    //构造器
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //显示结果
    public void showDijkstra() {
        vv.show();
    }

    //更新 index 下标顶点到周围顶点的距离和周围顶点的前驱顶点
    public void update(int index) {
        int len = 0;

        for (int j = 0; j < matrix[index].length; j++) {
            //len 含义：出发顶点到 index 顶点的距离+ 从 index 顶点到 j 顶点的距离的和
            len = vv.getDis(index) + matrix[index][j];

            //如果 j 顶点没有被访问过，并且 len 小于出发顶点到 j 顶点的距离，就需要更新
            if (!vv.in(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index);  //更新 j 顶点的前驱为 index 顶点
                vv.updateDis(j, len);    //更新出发顶点到 j 顶点的距离
            }
        }
    }

    //Dijkstra 算法实现
    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);  //更新 index 顶点到周围顶点的距离和前驱顶点

        for (int j = 1; j < vertex.length; j++) {
            index = vv.updateArr();     //选择并返回新的访问节点
            update(index);              //更新 index 顶点到周围顶点的距离和前驱结点
        }
    }

}


//已访问顶点集合
class VisitedVertex {
    public int[] already_arr;   //记录定点是否被访问过，1访问过，0没访问过，动态更新
    public int[] pre_visited;   //每个下标对应的值为前一个顶点下标，动态更新
    public int[] dis;   //记录出发点到其他所有顶点的距离，动态更新

    /**
     * 构造器
     *
     * @param length 顶点个数
     * @param index  出发顶点
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];

        //初始化 dis 数组
        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1;  //设置出发顶点被访问过
        this.dis[index] = 0;        //设置出发顶点的访问距离为0
    }

    /**
     * 功能：判断index 顶点是否被访问过
     *
     * @param index
     * @return 访问过返回 true ，否则返回 false
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 功能：更新出发顶点到 index 顶点的距离
     *
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 功能：更新 pre 顶点的前驱为 index 结点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 功能：返回出发顶点到 index 顶点的距离
     *
     * @param index
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 功能：继续选择并返回新的访问顶点，比如这里的 G 完后，就是 A 点作为新的访问顶点（注意不是出发顶点）
     *
     * @return
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        //更新 index 顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    //显示最后的结果,即将三个数组的情况输出
    public void show() {
        System.out.println("=========================");
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();

        //为了好看
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "(" + i + ")" + " ");
            } else {
                System.out.print("N");
            }
            count++;
        }
        System.out.println();
    }

}
