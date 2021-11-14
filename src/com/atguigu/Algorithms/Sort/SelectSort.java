package com.atguigu.Algorithms.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    /*
    选择排序是 不稳定 排序
     */
    public static void main(String[] args) {
        //int[] arr = {101, 34, 119, 1};

        //创建80000个随机数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000); //生成一个[0,800000) 数
        }

        System.out.println("排序前");
        //System.out.println(Arrays.toString(arr));
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        selectSort(arr);

        System.out.println("排序后");
        //System.out.println(Arrays.toString(arr));
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);

    }

    //选择排序
    //总结：
    //1.选择排序由两个循环组成
    //2.外循环控制次数，为 arr.length - 1 次
    //3.内循环遍历部分数组（头部最小值不用遍历）并得到最小值 min 和最小值的索引 minIndex ，
    //      1）内循环一定要遍历到最后一个数 即 停止条件为 j < arr.length
    //优化思路： 一次外循环最后 如果 midIndex 没有发生改变说明已经排序好了

    public static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {  //从索引i开始 循环 arr.length - 1 次
            int minIndex = i;   //minIndex指向最小值，是最小值的索引
            int min = arr[i];   //假定arr[i]是最小值

            //得到最小值min 和 最小值的索引minIndex
            for (int j = i + 1; j < arr.length; j++) {    // ！！！ 从索引为i后面一位开始循环 j < arr.length   一定要循环到最后一个数
                if (min > arr[j]) {   //说明 arr[j] 更小
                    min = arr[j];     // min 的值变成 arr[j]
                    minIndex = j;     //将 minIndex 指向 arr[j] 的索引
                }
            }

            if (minIndex != i) {    //即 循环一次 minIndex 的值没有发生改变 说明已经排好序了（minIndex 初始赋值为 i）
                arr[minIndex] = arr[i];
                arr[i] = min;
            }


            //arr[minIndex] = arr[i];   //把a[0]的值放到最小值的位置
            //arr[i] = min;             //把最小值放到a[0]

            //System.out.println("第"+(i+1)+"轮后~");
            //System.out.println(Arrays.toString(arr));   //  1,34,119,101
        }
    }


//第1轮
//原始数组：     101,34,119,1
//第一轮排序:    1,34,119,101



        /*

        //第1轮
        int minIndex=0; //minIndex指向最小值，是最小值的索引
        int min=arr[0];
        for (int j =1 ; j<arr.length;j++){
            if (min>arr[j]){    //说明假定的最小值，并不是最小
                min=arr[j];     //重置min
                minIndex=j;     //重置minIndex
            }
        }

        arr[minIndex]=arr[0];   //把a[0]的值放到最小值的位置
        arr[0]=min;             //把最小值放到a[0]

        System.out.println("第一轮后~");
        System.out.println(Arrays.toString(arr));   //  1,34,119,101
    }

         */

}