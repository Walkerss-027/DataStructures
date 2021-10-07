package com.atguigu.DataStructures.LinkedList;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //测试
//        System.out.println("双向链表的测试");
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        //添加元素
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);
//        doubleLinkedList.list();

        //按编号加入表元素
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero3);
        System.out.println("按顺序添加元素测试~");
        doubleLinkedList.list();


        //修改
//        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜","入云龙");
//        doubleLinkedList.update(newHeroNode);
//        System.out.println("修改后的链表情况");
//        doubleLinkedList.list();

        //删除
//        doubleLinkedList.del(3);
//        System.out.println("删除后的链表情况~");
//        doubleLinkedList.list();

    }

}


//创建一个双向链表类
class DoubleLinkedList {

    //先初始化一个头结点，头结点不要动
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        if (head == null) ;
        return head;
    }

    //遍历双向链表的方法
    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //头结点不能动，创建一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            //判断是否到了链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            temp = temp.next;//将temp后移
        }
    }

    //添加节点
    public void add(HeroNode2 heroNode) {

        //因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将 temp 后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //按顺序添加节点
    public void addByOrder(HeroNode2 heronode) {
        //因为头结点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        if (head.next == null) {   //判断是否为空链表
            heronode.pre = head;
            head.next = heronode;
            return;
        }
        HeroNode2 temp = head;
        boolean flag = false;//flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.no > heronode.no) {  //位置已经找到，就在temp后面插入
                heronode.pre = temp.pre;
                heronode.next = temp;
                temp.pre.next = heronode;
                temp.pre = heronode;
                break;
            } else if (temp.no == heronode.no) {   //想插入的编号已经存在
                flag = true;  //编号存在
                System.out.printf("准备插入的英雄编号%d已经存在，不能加入\n", heronode.no);
                break;
            }
            if (temp.next == null) {       //说明temp已经到链表的最后
                heronode.pre = temp;
                temp.next = heronode;
                break;
            }
            temp = temp.next;
        }
    }

    //更新节点
    public void update(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据 no编号
        //定义一个辅助变量
        HeroNode2 temp = head;
        boolean flag = false;   //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;  //已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else { //没有找到
            System.out.printf("没有找到编号%d的节点，不能修改\n", newHeroNode.no);
        }
    }

    //删除节点
    public void del(int no) {

        //判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next; //辅助变量（指针）
        boolean flag = false; //标志是否找到待删除节点
        while (true) {
            if (temp == null) {   //已经到链表的最后
                break;
            }
            if (temp.no == no) {
                //找到的待删除结点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; //temp后移，遍历
        }
        //判断flag
        if (flag) {  //找到
            //删除
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的%d 节点不存在\n", no);
        }
    }


}


//定义 HeroNode ,每个 HeroNode 对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;   //指向下一个节点
    public HeroNode2 pre;    //指向前一个结点

    //构造器
    public HeroNode2(int no, String name, String nickname) {
        //this 是为了声明.后面的变量是全局变量，级 class 类下的变量
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，我们重写 toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
