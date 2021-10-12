package com.atguigu.Algorithms.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {

    public static void main(String[] args) {
        //int[] arr={101,34,119,1,-1,89};

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

        insertSort(arr);

        System.out.println("排序后");
        //System.out.println(Arrays.toString(arr));
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    //插入排序
    public static void insertSort(int arr[]) {

        //使用for循环将代码简化
        for (int i = 0; i < arr.length; i++) {  //循环 arr.length 次

            int insertVal = arr[i];   //将待插入的数赋值给 insertVal
            int insertIndex = i - 1;  //即 arr[i] 前一个数的下标

            //给 insertVal 找到插入的位置
            //说明
            //从后往前遍历
            //1.insertIndex >= 0 保证在个insertVal 找插入位置，不越界
            //2.insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            //3.就要将 arr[insertIndex] 后移

            //insertIndex 从i-1 开始遍历 到0（边界）
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {  //每一次都是和 insertVal 的值比
                //将大的值往后放一位，insertVal 的值一直没有变
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;  //insertIndex 前移
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

            //System.out.println("第"+i+"轮插入");
            //System.out.println(Arrays.toString(arr));

        }


        /*  逐步分析

        //第一轮 {101,34,119,1} => {34,101,119,1}

        //定义待插入的数
        int insertVal=arr[1];   //insertVal=34
        int insertIndex = 1-1;  //即arr[1]的前面这个数的下标

        //给ubsertVal 找到插入的位置
        //说明
        //1.insertIndex >= 0 保证在个insertVal 找插入位置，不越界
        //2.insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        //3.就要将 arr[insertIndex] 后移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex +1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex +1] = insertVal;
        System.out.println("第一轮插入");
        System.out.println(Arrays.toString(arr));


        //第二轮 {34,101,119,1} => {34,101,119,1}

        //定义待插入的数
        insertVal=arr[2];
        insertIndex = 2-1;  //即arr[1]的前面这个数的下标

        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex +1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex +1] = insertVal;
        System.out.println("第二轮插入");
        System.out.println(Arrays.toString(arr));


        //第三轮 {34,101,119,1} => {1,34,101,119}

        //定义待插入的数
        insertVal=arr[3];
        insertIndex = 3-1;  //即arr[1]的前面这个数的下标

        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex +1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex +1] = insertVal;
        System.out.println("第三轮插入");
        System.out.println(Arrays.toString(arr));

         */

    }
}
