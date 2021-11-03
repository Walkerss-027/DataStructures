package com.atguigu.Search;

import java.util.Arrays;

public class FibonacciSearch {
    //斐波那契查找 本质还是二分法查找，只是引入了黄金分割点作为 mid 值来进行分割

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        System.out.println("index="+fibSearch(arr,1));
    }

    //因为后面与要用到 mid = low + F(k-1)-1 ,需要使用到斐波那契数列，因此需要先获取一个斐波那契数列
    //非递归方法得到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那契查找算法

    /**
     * @param a   数组
     * @param key 表示我们需要查找的关键码（值）
     * @return 返回对应的下标，如果没有返回-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;    //斐波那契分割数值的下标
        int mid = 0;
        int f[] = fib();  //获取斐波那契数列

        while (high > f[k] - 1) {   //获取斐波那契分割数值的下标（最接近顺序表长度，又比顺序表长度大的斐波那契数列值）
            //对 high > f[k] - 1 的理解： 顺序表长度比 k 对应的斐波那契数列值大时
            k++;
        }

        /*
        因为 f[k] 的值可能大于 a 的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向 a[]
        不足部分使用0填充
         */
        int[] temp = Arrays.copyOf(a, f[k]);

        /*
        实际需要使用 a 数组最后的数据填充 temp
        举例：
        temp = {1, 8, 10, 89, 1000, 1234,0,0} => {1, 8, 10, 89, 1000, 1234,1234,1234}
        for 循环的意义就是用最大值填补0
         */
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        //用 while 循环处理，找到数key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            /*
            对 mid = low + f[k - 1] - 1 的理解：
            1.由斐波那契数列 f[k] = f[k - 1] + f[k - 2] 的性质可以得到
            f[i] - 1 = （f[i - 1]-1） + （f[i - 2]-1）+ 1
            即 若顺序表的长度为 f[k]-1 ，则可以将其长度分成 f[k-1]-1 和 f[k-2]-1 两端
            2.类似，可将每一子段用相同方式分割
            3.顺序表长度不一定刚好等于 f[k]-1 ，所以需要将原来的顺序表长度增加值 f[k]-1 ，这里的 k 只要
            能使得 f[k]-1 恰好大于等于 n 即可，长度增加后，多出来的位置用这一段的最大值来填充

             */
            if (key < temp[mid]) {  //应继续想数组的前面查找
                high = mid - 1;
                /*
                为什么是 k--
                说明：
                1.全部元素=前面得元素+后面元素
                2.f[k] = f[k-1] + f[k-2]
                因为前面有 f[k-1] 个元素，就可以继续拆分成 f[k-1] = f[k-2] + f[k-3]
                即在 f[k-1] 的前面继续查找 k--
                即下次循环 mid = f[k-1-1]-1
                 */
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                /*
                为什么是 k-=2
                说明：
                1.全部元素=前面的元素+后面的元素
                2.f[k] = f[k-1] + f[k-2]
                3.因为后面有 f[k-2] 个元素，可以继续拆分 f[k-2] = f[k-3] + f[k-4]
                4.即在 f[k-2] 的前面查找 k-=2
                5.即下次循环 mid = f[k-1-2]-1
                 */
                k -= 2;
            } else { //找到
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;

    }
}
