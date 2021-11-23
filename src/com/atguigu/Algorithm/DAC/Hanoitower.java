package com.atguigu.Algorithm.DAC;

public class Hanoitower {
    //分治算法（Divide-and-Conquer）
    /*
    灵魂在于怎么分解问题，化为一个问题的简单重复递归
    思路：
        1.分解
        2.解决
        3.合并
    可以解决的经典问题：
        二分搜索
        大整数乘法
        棋盘覆盖
        合并排序
        快速排序
        线性时间选择
        最接近点对问题
        循环赛日程表
        汉诺塔
     */
    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }

    //汉诺塔的移动方法
    //使用分治算法

    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            //如果有 n >= 2 的情况，可以总是看作是两个盘 最下面的一个 和 上面所有的盘
            //1.先把上满所有盘 A->B ,移动过程会使用到 C
            hanoiTower(num - 1, a, c, b);
            //2.把最下面的盘 A->C ,
            System.out.println("第" + num + "个盘" + a + "->" + c);
            //3.把B塔所有盘从 B->C ,移动过程会使用到 a
            hanoiTower(num - 1, b, a, c);
        }
    }

}
