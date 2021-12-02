package com.atguigu.Algorithm.Horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {
    /*
    骑士周游算法
    深度优先 + 贪心算法(牛逼)
    应用场景：马踏棋盘

     */

    private static int X;   //棋盘的列数
    private static int Y;   //棋盘的行数

    //创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean visited[];
    //使用一个属性，标记是否棋盘的所有位置都被访问，如果为 true 表示成功
    private static boolean finished;

    public static void main(String[] args) {

        //测试
        X = 8;
        Y = 8;
        int row = 1;      //马儿的初始行
        int column = 1;   //马儿的初始列

        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];

        //测试一下耗时
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "毫秒");

        //输出棋盘最后情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }

    }

    /**
     * 功能：根据当前位置（Point 对象），计算马儿还能走哪些位置（Point），并放入到一个集合中（ArrayList），最多有8个位置
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {

        //创建一个 ArrayList
        ArrayList<Point> ps = new ArrayList<Point>();
        //创建一个 Point,马的走法
        Point p1 = new Point();
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    //根据当前这个一步的所有的下一步的选择位置，进行非递减排序,减少回溯的次数
    public static void sort(ArrayList<Point> ps) {

        ps.sort(new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {
                //获取到 o1 的下一步的所有位置的个数
                int count1 = next(o1).size();
                //获取到 o2 的下一步的所有位置的个数
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }

        });

    }

    /**
     * 功能：完成骑士周游问题的算法
     *
     * @param chessboard 棋盘
     * @param row        马的行，从0开始
     * @param column     马的列，从0开始
     * @param step       第几步，初始位置就是第1步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {

        chessboard[row][column] = step;
        visited[row * X + column] = true; //当前位置设置为已访问

        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));

        //对 ps 进行排序，排序的规则对 ps 的所有 Point 对象的下一步的位置的数目，进行非递减排序
        sort(ps);

        //遍历 ps
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);      //取出下一个可以走的位置
            //判断该点是否已经访问过
            if (!visited[p.y * X + p.x]) {   //说明还没访问过
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }

        //判断是否完成任务，没完成就将整个棋盘置0
        /*
        step < X * Y 成立有两种情况
            1.棋盘到目标位置，仍然没走完
            2.棋盘处于一个回溯过程
         */
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }


    }


}



