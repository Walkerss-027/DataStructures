package com.atguigu.Algorithm.Dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {
        /*
        动态规划
        0-1背包问题：
        逐渐增加背包容量
        判断：
            背包容量能不能容下新加入的物品[y/n]
            n:
            此时背包最大总价值与上一格（同一列的上一行）相同
            y：
            判断加入新物品后的背包总价值 > 不加新物品时的背包总价值[y/n]
                y：
                背包最大总价值为加入新物品后的价值
                n：
                此时背包最大总价值与上一格（同一列的上一行）相同
         */
        int[] w = {1, 4, 3};    //物品重量
        int[] val = {1500, 3000, 2000}; //物品价值
        int m = 4;    //背包容量
        int n = val.length;   //物品个数

        //创建二维数组
        //v[i][j] 表示在前 i 种物品中能够装入容量为 j 的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        //定义一个二维数组，记录放入的商品的情况
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行和第一列 , 本例可以省略
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;    //将第一列设置为0
        }
        for (int i = 0; i < v.length; i++) {
            v[0][i] = 0;    //将第一列设置为0
        }

        //根据公式动态规划处理
        for (int i = 1; i < v.length; i++) {    //不处理第一行
            for (int j = 1; j < v[i].length; j++) { //同理，不处理第一列
                //公式
                if (w[i - 1] > j) {     //新加物品的重量都比背包容量大（因为i是从1开始取得，所以i-1表示新加入物品，ps：w数组索引是从0开始的）
                    v[i][j] = v[i - 1][j];  //背包最大价值与上一格一样
                } else {                //新加物品的重量小于等于背包容量
                    /*
                    说明：
                    v[i - 1][j] ： 上一个（同一列的上一行）单元格装入的最大价值
                    v[i] ： 新加入物品的价值
                    v[i - 1][j - w[i-1]] ： 背包剩余 j - w[i-1] 空间时，放入前 i-1 种物品时得最大价值

                    因为 i 时从1开始，因此公式应该开始调整为
                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    为了记录商品存放到背包的情况，不能直接使用上面公式，需要使用 if-else 来体现公式
                     */
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {    //不放新物品背包总价值 < 放入新物品后的排列组合背包总价值
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前情况记录到 path
                        path[i][j] = 1;
                    } else {    //不妨新物品时背包价值更大
                        v[i][j] = v[i - 1][j];  //此时背包最大价值与上一格一样
                    }
                }
            }
        }

        //输出一下 v
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("=========================");
        //输入最后放入的哪些商品
        //下面方法会把所有情况都输出，但我们只需要最后的放入情况
//        for (int i = 0; i < path.length; i++) {
//            for (int j =0 ; j< path[i].length;j++){
//                if (path[i][j]==1){
//                    System.out.printf("第%d个商品放入到背包\n",i);
//                }
//            }
//        }
        int i = path.length - 1;     //行的最大下标
        int j = path[0].length - 1;    //列的最大下标，也即背包容量
        while (i > 0 && j>0) {    //从 path 的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1]; //把第i-1种物品拿出背包（背包容量j - 第i-1种物品重量w[i-1]）
            }
            i--;
        }
    }
}
