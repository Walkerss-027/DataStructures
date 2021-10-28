package com.atguigu.Algorithms.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
        //int[] arr = {53, 3, 542, 748, 14, 214};

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000); //生成一个[0,800000) 数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        radixSort(arr);
        //System.out.println(Arrays.toString(arr));

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);


    }


    public static void radixSort(int[] arr) {


        //找到最大数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int maxLength = (max + "").length();    //找到最大位数


        /*
        定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        说明：
        1.二维数组包含10个一维数组
        2.为了放置在放入数的时候，数据溢出，则每一个一维数组（桶），大小定为arr.length
        3.很明确，基数排序是空间换时间的经典算法
        */

        int[][] bucket = new int[10][arr.length];

        /*
        为了记录每个桶中实际放了多少数据，定义一个一维数组来记录各个桶的每次放入的数据个数
        比如 bucketElementCounts[0] ,记录的就是 bucket[0] 桶的放入数据个数
         */

        int[] bucketElementCounts = new int[10];    //定义10个桶


        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //（对每个元素的个位进行排序处理）,第一次是个位，第二次是十位，第三次是百位...

            //先放入桶中
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;     //取出每个元素对应位值
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];   //将数组元素放入桶中
                bucketElementCounts[digitOfElement]++;  //把这个桶的指针往下移一位
            }
            //把桶中的数据按排序放入原数组
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {  //k为桶
                if (bucketElementCounts[k] != 0) {  //不是空桶
                    for (int l = 0; l < bucketElementCounts[k]; l++) {  //遍历此桶
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;   //每次遍历一个桶后应把桶里元素清零！！！
            }
            //System.out.println("第"+(i+1)+"轮，对个位的排序处理 arr =" + Arrays.toString(arr));

        }


        /*
        //第一轮（对每个元素的个位进行排序处理）

        //先放入桶中
        for (int j = 0; j < arr.length; j++) {
            int digitOfElement = arr[j] % 10;     //取出每个元素个位值
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];   //将数组元素放入桶中
            bucketElementCounts[digitOfElement]++;  //把这个桶的指针往下移一位
        }
        //把桶中的数据按排序放入原数组
        int index = 0;
        for (int k = 0; k < bucketElementCounts.length; k++) {  //k为桶
            if (bucketElementCounts[k] != 0) {  //不是空桶
                for (int l = 0; l < bucketElementCounts[k]; l++) {  //遍历此桶
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElementCounts[k] = 0;   //每次遍历一个桶后应把桶里元素清零！！！
        }
        System.out.println("第一轮，对个位的排序处理 arr =" + Arrays.toString(arr));


        //第二轮（对每个元素的十位进行排序处理）

        //先放入桶中
        for (int j = 0; j < arr.length; j++) {
            int digitOfElement = arr[j] / 10 % 10;     //取出每个元素十位值
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];   //将数组元素放入桶中
            bucketElementCounts[digitOfElement]++;  //把这个桶的指针往下移一位
        }
        //把桶中的数据按排序放入原数组
        index = 0;
        for (int k = 0; k < bucketElementCounts.length; k++) {  //k为桶
            if (bucketElementCounts[k] != 0) {  //不是空桶
                for (int l = 0; l < bucketElementCounts[k]; l++) {  //遍历此桶
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElementCounts[k] = 0;   //每次遍历一个桶后应把桶里元素清零！！！
        }
        System.out.println("第二轮，对个位的排序处理 arr =" + Arrays.toString(arr));


        //第三轮（对每个元素的十位进行排序处理）

        //先放入桶中
        for (int j = 0; j < arr.length; j++) {
            int digitOfElement = arr[j] / 100;     //取出每个元素百位值
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];   //将数组元素放入桶中
            bucketElementCounts[digitOfElement]++;  //把这个桶的指针往下移一位
        }
        //把桶中的数据按排序放入原数组
        index = 0;
        for (int k = 0; k < bucketElementCounts.length; k++) {  //k为桶
            if (bucketElementCounts[k] != 0) {  //不是空桶
                for (int l = 0; l < bucketElementCounts[k]; l++) {  //遍历此桶
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElementCounts[k] = 0;   //每次遍历一个桶后应把桶里元素清零！！！
        }
        System.out.println("第三轮，对十位的排序处理 arr =" + Arrays.toString(arr));
         */

    }
}

