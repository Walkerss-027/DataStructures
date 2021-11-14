package com.atguigu.Algorithms.Sort;

import java.util.Arrays;
import java.util.spi.AbstractResourceBundleProvider;

public class HeapSort {
    /*
    堆排序是 不稳定 排序
    堆排序是一种选择排序
    堆是一个具有以下性质的完全二叉树
        1.每个结点的值都大于等于其左右孩子结点的值，称为大顶堆（没有要求左右孩子值大小关系）
            大顶堆特点：arr[i] >= arr[2*i+1] && arr[i] >= arr[2*i+2] i对应第几个结点，从0开始
        2.每个结点的值都小于等于其左右孩子结点的值，称为小顶堆
            小顶堆特点：arr[i] <= arr[2*i+1] && arr[i] <= arr[2*i+2]

    堆排序的基本思想：
    1.将待排序序列构造成一个堆，根据升序降序需求选择大顶堆（升序）或小顶堆（降序）
    2.将堆顶元素与末尾元素交换，将最大元素”沉“到数组末端
    3.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
     */
    public static void main(String[] args) {
        //要求将数组进行升序排序
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);

    }


    //编写一个堆排序的方法
    public static void heapSort(int arr[]) {
        System.out.println("堆排序！！");
        int temp = 0;

//        //分步完成
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次"+ Arrays.toString(arr));    // 4, 9, 8, 5, 6
//
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第二次"+ Arrays.toString(arr));    // 9, 6, 8, 5, 4

        //最终代码
        //for 循环执行完毕后除了索引为 1 2 的元素（即根节点的左右子结点）外其他元素都完成了降序排序 => [9, 6, 8, 5, 4]
        for (int i = arr.length / 2 - 1; i >= 0; i--) {     // arr.length/2 -1 为总非叶子节点数(也是最后一个非叶子节点的索引，之前所有节点都是叶子节点，可以联想一下数组转换成二叉树是的排列过程)
            adjustHeap(arr, i, arr.length);                 //相当于从下往上进行堆排序
        }

        //调整树状结构
        /*
        1.将栈顶元素与末尾元素交换，将最大的元素“沉”到数组末端
        2.调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
         */
        for (int j = arr.length - 1; j > 0; j--) {
            //每次交换 arr[0] 和 arr[j] 的位置，j 从 arr.length - 1 取到 0
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
            System.out.println(j);
            System.out.println("数组=" + Arrays.toString(arr));
        }
//        System.out.println("数组=" + Arrays.toString(arr));
    }


    //将一个数组（二叉树），调整成一个大顶堆
    /**
     * 功能：完成  i 对应的非叶子节点的树 调整成大顶堆
     * 举例： int[] arr = {4,6,8,5,9}; => i = 1 => adjustHeap => 得到{ 4, 9, 8, 5, 6}
     * 如果再次调用 adjustHeap 传入的是 i = 0 => 得到 {9, 6, 8, 5, 4}
     *
     * @param arr    待调整数组
     * @param i      非叶子节点在数组中的索引
     * @param length 表示对多少个元素继续调整，length 逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; //先取出当前元素的值，保存在临时变量
        //开始调整
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {    //k指向的是 i 结点的左子结点，一次循环结束 k 变为 左子结点的左子结点（k = k * 2 + 1）
            if (k + 1 < length && arr[k] < arr[k + 1]) {    //如果左子结点的值小于右子节点的值
                k++;    // k 变为右子结点
            }
            if (arr[k] > temp) {    //子结点的值比当前节点值大
                arr[i] = arr[k];    //交换当前结点和子结点（在数组中交换）
                //arr[k] = temp;
                i = k;  //！！！ i 变为左（右）子结点
            } else {
                break;
            }
        }
        // for 循环结束后，就实现了以 i 为父结点的二叉树的最大值放在了父结点（堆顶）
        arr[i] = temp;
    }
}
