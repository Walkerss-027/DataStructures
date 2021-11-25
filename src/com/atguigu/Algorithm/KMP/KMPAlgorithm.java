package com.atguigu.Algorithm.KMP;

import java.util.Arrays;
import java.util.Stack;

public class KMPAlgorithm {
    /*
    KMP算法解决的是字符串匹配问题
     */

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
//        String str2="BBC";

        int[] next = kmpNext(str2); //next[]就是部分匹配表
        System.out.println("next=" + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index);
    }

    /**
     * KMP 搜索算法
     * 就是用获取前缀表的思路去加一个搜索
     *
     * @param str1 源字符串
     * @param str2 子串
     * @param next 子串对应的部分匹配表
     * @return 如果是-1就是没有匹配到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {

        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {

            //需要处理 str1.charAt(i) != str2.charAt(j),去调整j的大小
            //KMP算法的核心点
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }


            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {    //找到了
                return i - j + 1;
            }
        }
        return -1;
    }


    //获取到一个字符串（子串自己！！！）的部分匹配值
    public static int[] kmpNext(String dest) {
        /*
        以下都是对子串的操作：
        处理：
            前后缀不相同的情况
            前后缀相同的情况
            next数组
         */

        //创建一个 next 数组保存部分匹配值
        int[] next = new int[dest.length()];    //记录状态
        next[0] = 0;  //如果字符串长度为1 部分匹配值为0

        //遍历子串,因为 j=0 所以 i=1 从第二个字符开始遍历比较,j为部分匹配值(也作为指针使用)
        for (int i = 1, j = 0; i < dest.length(); i++) {

            // i 指针指向的的字符和 j 指针指向的字符不相等，在 i 当前的循环里对 j 循环回溯直到字符串最左边为止或者有字符匹配退出循环
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) { //匹配失败 循环回溯处理
                /*
                //（kmp算法的核心）状态回退！！！
                因为重复字符长度一定小于前面不重复字符长度，所以 j 最多指向next[]部分匹配值为1的位置
                那么next[j-1]的值就一定为0
                 */
                j = next[j - 1];    //回溯处理，让j左移到上一个状态，如果字符一直不匹配就一直while向上一个状态回溯
            }

            //当dest.charAt(i) == dest.charAt(j) 时，部分匹配值+1
            if (dest.charAt(i) == dest.charAt(j)) { //匹配成功
                j++;
            }
            next[i] = j;    // next[i] 下标标记为 j（next[i]对应的部分匹配值为j）
        }

        return next;
    }
}
