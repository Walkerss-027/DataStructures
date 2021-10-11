package com.atguigu.Algorithms.Sort;

import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
    }


    public static void shellSort(int[] arr) {
        int temp = 0;
        //希尔排序第一轮排序
        for (int i = 5; i < arr.length; i++) {
            for (int j = i - 5; j >= 0; j = j - 5) {    //遍历各组种所有的元素（共5组，每组有2个元素），步长为5
                if (arr[j] > arr[j + 5]) {  //当前元素如果大于加上步长后的元素则进行交换（大的换后边）
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("希尔排序第一轮的结果"+ Arrays.toString(arr));

        //希尔排序第二轮排序+
        //因为第2轮排序，是将10个数据分成了5/2 = 2组
        for (int i = 5; i < arr.length; i++) {
            for (int j = i - 5; j >= 0; j = j - 5) {
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("希尔排序第一轮的结果"+ Arrays.toString(arr));
    }
}
