package com.atguigu.LinkedList;

public class SingleLinkedListDemo {

}

//定义 SingleLinkedList 管理我们的英雄
class SingleLinkedList{
    //先初始化一个头结点，头结点不要动
    private HeroNode head=new HeroNode(0,"","");

//   添加节点到单向链表
//    思路，当不考虑编号顺序时
//    1.找到当前链表的最后节点
//    2.将最后这个节点的next 指向新节点
    public void add(HeroNode heroNode){

        //因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp=head;
        //遍历链表，找到最后
        while (true){
            //找到链表的最后
            if (temp.next==null){
                break;
            }
            //如果没有找到最后，将 temp 后移
            temp=temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next 指向新的节点
        temp.next=heroNode;
    }

    //显示链表[遍历]
    public void list(){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head.next;
        while (true){
            if (temp.next==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;//将temp后移
        }
    }

}


//定义 HeroNode ,每个 HeroNode 对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点
    //构造器
    public HeroNode(int no,String name,String  nickname){
        //this 是为了声明.后面的变量是全局变量，级 class 类下的变量
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }
    //为了显示方法，我们重写 toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", next=" + next +
                '}';
    }
}
