package com.atguigu.DataStructures.SparseArray;

public class SparseArray {

    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0：表示没有棋子，1表示黑子，2表示蓝子
        int chessArr1[][]=new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        chessArr1[4][4]=2;
        //输出原始的二维数组
        System.out.println("原始的二维数组~~");
        for (int[] row:chessArr1){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二维数组转换为稀疏数组
        //1.遍历数组得到有效值个数
        int sum=0;
        for (int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                if (chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int sparseArr[][]=new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;

        //遍历二维数组，将非0值存放在sparseArr中
        int count=0;
        for (int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                if (chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为~~~");
        for (int i=0 ;i < sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();

        //将稀疏数组恢复成二维数组

        /*
        1.先通过稀疏数组创建原始二维数组
        2.再读取稀疏数组后几行数据并赋值给二维数组
        */

        System.out.println("恢复后的二维数组为~~~");

        int cheeArr2[][]=new int[sparseArr[0][0]][sparseArr[0][1]];


        for (int i=1;i<sparseArr.length;i++){
            cheeArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
            }

        for (int[] row:cheeArr2){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
}
