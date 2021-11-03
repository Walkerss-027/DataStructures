package com.atguigu.Search;

import java.util.Arrays;

public class InsertValueSearch {
    /*
    注意事项：
    1.数据量较大，关键字分布比较均匀的查找表用插值查找速度比较快
    2.关键字分布不均匀的情况，插值查找不一定比折半查找要好
     */
    public static void main(String[] args) {

        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        //System.out.println(Arrays.toString(arr));

        int index=insertValueSearch(arr,0,arr.length-1,100);
        System.out.println("index="+index);
    }

    //编写插值查找算法

    /**
     * @param arr     数组
     * @param left    左索引
     * @param right   右索引
     * @param findVal 查找值
     * @return 如果找到返回下标，如果没有找到，返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        //求出 mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal=arr[mid];
        if (findVal>midVal){    //向右递归
            return insertValueSearch(arr,mid+1,right,findVal);
        }else if (findVal<midVal){  //想做递归
            return insertValueSearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }
}
