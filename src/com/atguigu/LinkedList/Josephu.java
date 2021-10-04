package com.atguigu.LinkedList;

public class Josephu {

    public static void main(String[] args) {

    }

}

//创建一个环形环形的单向链表
class CirclleSingleLinkedList{
    //创建一个first节点。当前没有编号
    private Boy first = new Boy(-1);
    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums){

    }
}


//创建一个Boy类，表示一个节点
class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
        this.no=no;
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
