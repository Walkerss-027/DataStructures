package com.atguigu.LinkedList;

public class Josephu {


    //约瑟夫问题-小孩出圈（循环队列出圈）


    public static void main(String[] args) {
        //测试
        CirclleSingleLinkedList circlleSingleLinkedList = new CirclleSingleLinkedList();
        circlleSingleLinkedList.addBoy(25);
        circlleSingleLinkedList.showBoy();

        //测试小孩出圈是否正确
        circlleSingleLinkedList.countBoy(1,2,25);

    }

}

//创建一个环形环形的单向链表
class CirclleSingleLinkedList {

    //创建一个first节点。当前没有编号
    private Boy first = null;

    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {

        //nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }

        Boy curBoy = null;  //辅助指针，帮助构建环形链表

        //使用for来创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);   //构成环
                curBoy = first;   //让curboy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }

        }

    }


    public void showBoy() {
        if (first == null) {
            //判断链表是否为空
            System.out.println("没有任何小孩~");
            return;
        }
        Boy curBoy = first;   //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        while (true) {
            System.out.printf("小孩的编号为 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {   //说明遍历完了
                break;
            }
            curBoy = curBoy.getNext();    //curBoy后移
        }


    }


    public void countBoy(int startNo, int coutNum, int nums) {
        if (first == null || startNo < 1 || coutNum > nums) {   ///先对数据进行校验
            System.out.println("参数输入有误，请重新输入~");
            return;
        }
        Boy helper = first;   //创建helper 辅助指针事先指向环形链表最后的节点
        while (true) {
            if (helper.getNext() == first) {   //保证helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让 first 和 helper 移动 k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (true) {   //循环至圈中只有一个节点
            if (helper == first) {
                break;
            }
            for (int i = 0; i < coutNum - 1; i++) {   //让first和helper指针同时移动countNum -1
                first = first.getNext();
                helper = helper.getNext();
            }
            //此时first指向的就是要出圈的小孩
            System.out.printf("小孩%d出圈\n", first.getNo());

            first = first.getNext();
            helper.setNext(first);

        }
        System.out.printf("最后留在圈中的小孩编号%d",first.getNo());
    }
}


//创建一个Boy类，表示一个节点
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
