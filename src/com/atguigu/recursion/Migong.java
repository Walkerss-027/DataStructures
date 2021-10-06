package com.atguigu.recursion;

public class Migong {

    public static void main(String[] args) {
        //创建一个二维数组
        //地图
        int[][] map=new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for (int i =0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        //左右全部置为1
        for (int j=1;j<7;j++){
            map[j][0]=1;
            map[j][6]=1;
        }

        //输出地图
        System.out.println("地图的情况");

        for (int[] ele:map){
            for (int data:ele){
                System.out.print(data+" ");
            }
            System.out.println();
        }

        /*
        for (int i=0;i<8;i++){
            for (int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

         */
    }
}
