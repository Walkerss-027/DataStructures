package com.atguigu.DataStructures.recursion;

public class Migong {

    public static void main(String[] args) {
        //创建一个二维数组
        //地图
        int[][] map=new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for (int i =0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        //左右全部置为1
        for (int j=1;j<7;j++){
            map[j][0]=1;
            map[j][6]=1;
        }
        //设置挡板，1表示
        map[3][1]=1;
        map[3][2]=1;

        //输出地图
        System.out.println("地图的情况");

        for (int[] ele:map){        //遍历二维数组的第行数据（数组）
            for (int data:ele){     //遍历取出的数组
                System.out.print(data+" ");
            }
            System.out.println();
        }
        System.out.println();

        //使用递归回溯给小球找路
//        setWay(map,1,1);
        setWay2(map,1,1);


        //输出新地图，小球走过，并标识的地图
        for (int i=0;i<8;i++){
            for (int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();

    }

    //使用递归回溯来给小球找路
    //说明
    //1.map 表示地图
    //2.i，j表示从地图的哪个位置开始出发（1，1）
    //3.如果小球能到map[6][5]位置，则说明通路找到
    //4.约定：当map[i][j] 为0表示该点没有走过，当为1时表示墙，2表示通路可以走；3表示该点已经走过，但是走不通
    //5.再走迷宫式，需要确定一个策略（方法） 下->右->上->左，如果该点走不通，再回溯

    /**
     *
     * @param map   表示地图
     * @param i     从哪个位置开始找
     * @param j
     * @return      如果找到通路，就返回true，否则返回false
     */
    public  static boolean setWay(int[][] map,int i ,int j){
        if (map[6][5]==2){  //通路已经找到
            return true;
        }else {
            if (map[i][j]==0){  //当前点还没走过
                //按照策略 下->右->上->左
                map[i][j]=2;    //假定该点可以走
                if (setWay(map,i+1,j)){     //向下走
                    return true;
                }else if (setWay(map,i,j+1)){   //向右走
                    return true;
                }else if (setWay(map,i-1,j)){   //向上走
                    return true;
                }else if (setWay(map,i,j-1)){   //向左走
                    return true;
                }else {
                    //说明该点走不通
                    map[i][j]=3;
                    return false;
                }
            } else {    //如果map[i][j] != 0，可能是1，2，3
                return false;
            }
        }
    }

    //修改找路的策略，改成 上->右->下->左
    public  static boolean setWay2(int[][] map,int i ,int j){   //返回 boolean 值用于下面 if 递归时的判断
        if (map[6][5]==2){  //通路已经找到
            return true;
        }else {
            if (map[i][j]==0){  //当前点还没走过
                //按照策略 下->右->上->左
                map[i][j]=2;    //假定该点可以走
                if (setWay2(map,i-1,j)){     //向上走
                    return true;
                }else if (setWay2(map,i,j+1)){   //向右走
                    return true;
                }else if (setWay2(map,i+1,j)){   //向下走
                    return true;
                }else if (setWay2(map,i,j-1)){   //向左走
                    return true;
                }else {
                    //说明该点走不通
                    map[i][j]=3;
                    return false;
                }
            } else {    //如果map[i][j] != 0，可能是1，2，3
                return false;
            }
        }
    }


}


