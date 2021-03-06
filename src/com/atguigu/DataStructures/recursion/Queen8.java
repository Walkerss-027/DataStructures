package com.atguigu.DataStructures.recursion;

public class Queen8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置的结果，比如 arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];     //使用一维数组的原因是 一维数组的索引就可以代表列，其中放的 value 可以用来表示行高，实现了棋盘的功能
    static int count = 0;
    static int judgecount=0;
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);    //check内有print()方法 所以不用打印
        System.out.printf("一共有%d种方法", count);
        System.out.printf("一共判断冲突的次数%d", judgecount);
    }


    //编写一个方法，放置第n个皇后
    //特别注意：check是每一次递归时，都有for(int i =0 ; i< max;i++),因此会有回溯
    private void check(int n) {

        //递归停止的条件
        if (n == max) {  //n=8，其实8个皇后就已经放好了 即一种可能执行到了最后
            print();
            return;
        }

        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            judgecount++;
            array[n] = i;   //先把当前这个皇后n，放到该行的第1列
            if (judge(n)) { //判断当放置第n个皇后到i列时，是否冲突
                check(n + 1);   //接着放n+1个皇后，即开始递归 这里check了多少次就执行了多少次print count就++了多少次
            }
             //如果冲突，就继续执行 array[n]=1;即将第n个皇后，放置再本行的后移的一个位置
        }
    }


    //查看当我们放置第n个皇后，就去检测该皇后是否和前面已经拜访的皇后冲突

    /**
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //说明
            //1.array[i] == array[n] 表示判断第n个皇后是否和前面的n-1个皇后再同一列
            //2.Math.abs(n - 1) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i个皇后是否在同一斜线(斜率)
            //n-i 相当于行距，Math.abs(array[n] - array[i]) 相当于列距
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后拜访的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
