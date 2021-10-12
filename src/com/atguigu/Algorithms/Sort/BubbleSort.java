package com.atguigu.Algorithms.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.SimpleTimeZone;

public class BubbleSort {

    public static void main(String[] args) {

//        int[] arr = {3, 9, -1, 10, 20};
//
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));


        int[] arr=new int[80000];
        for (int i=0; i<80000;i++){
            arr[i]=(int)(Math.random()*800000); //生成一个[0,800000) 数
        }

        Date date1=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str=simpleDateFormat.format(date1);
        System.out.println("排序前的时间是="+date1Str);

        //测试冒泡排序
        Bubblesort(arr);

        Date date2=new Date();
        String date2Str=simpleDateFormat.format(date2);
        System.out.println("排序后的时间是="+date2Str);


//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));

        /*

        System.out.println("第一趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第二趟排序，就是将第二大的数排在倒数第二位
        for (int j = 0; j < arr.length - 2; j++) {
            if (arr[j] > arr[j + 1]) {  //如果前面的数比后面的数大，则交换
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }

        System.out.println("第二趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第三趟排序，就是将第二大的数排在倒数第二位
        for (int j = 0; j < arr.length - 3; j++) {
            if (arr[j] > arr[j + 1]) {  //如果前面的数比后面的数大，则交换
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }

        System.out.println("第三趟排序后的数组");
        System.out.println(Arrays.toString(arr));

    */
    }


    //将前面的冒泡排序算法，封装成一个方法
    //总结：
    //每一次外循环把 最大的 放在最后
    //1.冒泡排序由两层循环组成，时间复杂度为 O(n^2) 是一种稳定的排序方法，但数据量较大时花费的时间较长
    //2.外循环控制循环的次数，循环 i < arr.length - 1 次
    //3.内循环遍历数组，循环 arr.length - 1 - i 次，在内循环里对比 arr[j] 和 arr[j + 1] 的大小，并对相邻元素进行排序
    //优化思路：在一次外循环最后 flag仍为 false 即一次交换都没有发生过，则说明排序完成
    public static void Bubblesort(int[] arr) {
        int temp = 0;   //临时变量
        boolean flag = false; //标识变量，表示是否进行过交换
        //第一趟排序，就是把最大的数放在最后
        for (int i = 0; i < arr.length - 1; i++) {      //外循环控制循环的次数为 arr.length - 1 次
            for (int j = 0; j < arr.length - 1 - i; j++) {      //内循环控制从第 1 个开始遍历 循环 arr.length - 1 - i 次 即遍历到倒数第二个元素
                if (arr[j] > arr[j + 1]) {  //如果前面的数比后面的数大，则交换
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            //System.out.println("第" + (i + 1) + "趟排序后的数组");
            //System.out.println(Arrays.toString(arr));

            if (!flag) {  //再一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; //重置flag,进行下次判断
            }

        }

    }


}


