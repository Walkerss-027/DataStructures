package com.atguigu.Algorithms.Sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));

        selectSort(arr);

        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));

    }

    //选择排序
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i; //minIndex指向最小值，是最小值的索引
            int min = arr[i];

            //得到最小值min 和 最小值的索引minIndex
            for (int j = i+1; j < arr.length; j++) {    // ！！！ j < arr.length   一定要循环到最后一个数
                if (min > arr[j]) {    //说明假定的最小值，并不是最小
                    min = arr[j];     //重置min
                    minIndex = j;     //重置minIndex
                }
            }


            arr[minIndex] = arr[i];   //把a[0]的值放到最小值的位置
            arr[i] = min;             //把最小值放到a[0]

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