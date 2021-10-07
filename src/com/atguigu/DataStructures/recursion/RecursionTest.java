package com.atguigu.DataStructures.recursion;

public class RecursionTest {

    //适合用递归解决的问题：8皇后问题，汉诺塔，阶乘问题，迷宫问题，球和蓝子的问题

    //用到递归的算法：快排，归并排序，二分查找，分治算法

    //用栈解决的问题 ——> 递归代码比较简洁

    public static void main(String[] args) {
        //通过打印问题，回顾递归调用机制
        test(4);
        factorial(3);

    }
    //打印问题
    public static void test(int n){
        if (n>2){
            test(n-1);
        }
        System.out.println("n="+n);
    }
    //乘除问题
    public static int factorial(int n){
        if (n==1){
            return 1;
        }else {
            return factorial(n-1)*n;
        }

    }
}
