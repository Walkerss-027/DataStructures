package com.atguigu.Algorithm.BinarySearchNoRecursion;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        //测试
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 8);
        System.out.println("index=" + index);
    }

    /**
     * 功能：二分查找的非递归实现
     *
     * @param arr    待查找的数组
     * @param target 待查找的数
     * @return 返回对应的下标，-1表示没有找到
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) { //中间比要找的值大
                right = mid - 1;
            } else {                        //中间比要找的值小
                left = mid + 1;
            }
        }
        return -1;
    }
}
