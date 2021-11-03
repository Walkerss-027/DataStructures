package com.atguigu.Search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};

//        int resIndex = binarySearch(arr, 0, arr.length - 1, 1234);
//        System.out.println("resIndex=" + resIndex);
        List resIndexList=binarySearch2(arr,0,arr.length-1,1000);
        System.out.println("resIndexList="+resIndexList);
    }

    /**
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，没有找到就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        //当 left >right时说明递归整个数组，但是没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {    //向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {  //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        //当 left >right时说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {    //向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {  //向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            /*
            思路分析：
            1.在找到 mid 索引值后，不要马上返回。
            2.向 mid 索引值的左边扫描，将所有满足 1000 的元素下标，加入到集合 ArrayList
            3.向 mid 索引值的右边扫描，将所有满足 1000 的元素下标，加入到集合 ArrayList
            4.返回 ArrayList
             */
            ArrayList<Integer> resIndexList = new ArrayList<Integer>();

            //向 mid 左边索引
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp -= 1;
            }
            resIndexList.add(mid);

            //向 mid 右边索引
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }
    }
}
