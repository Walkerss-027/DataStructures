package com.atguigu.Algorithms.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {

    //希尔排序是一种插入排序的改进方法，也称递减增量排序算法，时间复杂度为 O(n^(1.3-2))

    //希尔算法的背景：
    //1.插入排序对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率；
    //2.但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位

    //希尔排序基本思想：把整个序列分割成若干个子序列分别进行直接插入排序，待整个序列种的记录“基本有序”时，再对全体记录依次直接插入排序

    public static void main(String[] args) {
        //int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        //创建80000个随机数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000); //生成一个[0,800000) 数
        }

        System.out.println("排序前");
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        //shellSort(arr);   //交换式
        shellSort2(arr);    //移位方式

        System.out.println("排序后");
        //System.out.println(Arrays.toString(arr));
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);


    }

    public static void shellSort(int[] arr) {

        int temp = 0;
        int count = 0;

        //使用循环处理
        for (int gap = arr.length / 2; gap > 0; gap /= 2) { //gap 表示步长，每一次循环过后步长进行一次 /2 操作
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j = j - gap) {    //从右向左 遍历各组所有的元素（共 gap 组，每组有个元素），步长为 gap
                    if (arr[j] > arr[j + gap]) {  //当前元素如果大于加上步长后的元素则进行交换（大的换后边）
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("希尔排序第" + (++count) + "轮的结果" + Arrays.toString(arr));
        }


        /*

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
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0; j = j - 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("希尔排序第二轮的结果"+ Arrays.toString(arr));

        //希尔排序第三轮排序+
        //因为第3轮排序，是将10个数据分成了2/2 = 1组
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j = j - 1) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序第三轮的结果"+ Arrays.toString(arr));

         */

    }

    public static void shellSort2(int arr[]) {

        //增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {    //相当与从第 gap 元素开始从左向右遍历数组 因为 后面要从 j - gap 开始从右向左遍历
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {    //右边的小于左边
                    while (j - gap >= 0 && temp < arr[j - gap]) {   //while 结束时 j 即为应该插入的位置
                        //移动
                        arr[j] = arr[j - gap];  //把大的往后移
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }

    }
}
