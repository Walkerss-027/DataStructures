package com.atguigu.Algorithms.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    //快速排序核心思想：双向指针向中间移动，比较元素大小并换位，再加上递归

    public static void main(String[] args) {
        //int[] arr = {-9, 78, 0, 23, -567, 70,-1,200,4567};

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000); //生成一个[0,800000) 数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        quickSort(arr, 0, arr.length - 1);
        //System.out.println(Arrays.toString(arr));

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);

    }


    public static void quickSort(int[] arr, int left, int right) {
        int low = left;   //左下标
        int high = right;  //右下标
        int pivot = arr[(left + right) / 2];    //中间位置的值
        int temp = 0; //临时变量 用来交换元素

        //while 循环得到的结果是把比 pivot 小的值放左边 比 pivot 大的值放右边
        while (low < high) { //左右下标未相遇

            while (arr[low] < pivot) {  //目的是从左往右找，直到找到大于等于 pivot 值才退出
                low += 1;
            }

            while (arr[high] > pivot) { //目的是从右往左找，直到找到小于等于 pivot 值才退出
                high -= 1;
            }

            if (low >= high) {  //l >= r 说明 pivot 左边的值全部小于等于 pivot 右边的值全部大于等于 pivot
                break;
            }

            //交换 arr[low] 和 arr[high] 的值
            temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;

            //对特殊情况的处理
            if (arr[low] == pivot) {  //交换完后，发现 arr[l] == pivot ， r-- 前移
                high -= 1;
            }

            if (arr[high] == pivot) {  //交换完后，发现 arr[l] == pivot ， l++ 后移
                low += 1;
            }
        }

        //如果 l == r ,必须 l++,r--,否则会出现栈溢出
        if (low == high) {
            low += 1; //把 l 指向 pivot 右边
            high -= 1; //把 r 指向 pivot 左边
        }

        //递归调用，排序左子集合
        if (left < high) {
            quickSort(arr, left, high);
        }
        //递归调用，排序右子集合
        if (right > low) {
            quickSort(arr, low, right);
        }
    }


    //CSDN 两个哨兵 ，基准设在首元素 实现快排 比上面清晰一点

    /*
       public static void quickSort(int[] arr, int low, int high) {
        // low,high 为每次处理数组时的首、尾元素索引

        //当low==high是表示该序列只有一个元素，不必排序了
        if (low >= high) {
            return;
        }
        // 选出哨兵元素和基准元素。这里左边的哨兵元素也是基准元素
        int i = low, j = high, base = arr[low];
        while (i < j) {
            //!!! 基准设在最左边要先从右边开始
            //右边哨兵从后向前找
            while (arr[j] >= base) {
                j--;
            }
            //左边哨兵从前向后找
            while (arr[i] <= base) {
                i++;
            }
            swap(arr,i,j);  //交换元素
        }
        swap(arr,low,j);  //基准元素与右哨兵交换

        //递归调用，排序左子集合和右子集合
        quickSort(arr,low,j-1);
        quickSort(arr,j+1,high);

    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

     */


}
