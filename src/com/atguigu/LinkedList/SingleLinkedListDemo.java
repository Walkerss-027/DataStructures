package com.atguigu.LinkedList;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //创建节点
        HeroNode hero1=new HeroNode(1,"宋江","及时雨");
        HeroNode hero2=new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3=new HeroNode(3,"吴用","智多星");
        HeroNode hero4=new HeroNode(4,"林冲","豹子头");

        //创建要给的链表
        SingleLinkedList singleLinkedList=new SingleLinkedList();
        //加入表元素
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        //按编号加入表元素
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero3);

        //显示数据
        singleLinkedList.list();

    }

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

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //（如果有这个排名，则添加失败，并给出提示）

    //思路：1.通过遍历来查找节点应该插入的位置
    //     2.遍历过程中有3种需要停止遍历的情况
    //          1）遍历到链表最后了
    //          2）找到要指定位置（当前遍历的节点的next节点的排名 > 要插入的英雄节点排名[小的在前面]）
    //          3）链表中此编号英雄已存在
    //       如果不存在此3种情况则一直向后遍历
    //     3.如果已存在则输出语句，否则将当前节点插入队列，方式为：
    //          1）先加链子：将要插入的节点连到temp.next节点，即 heronode=temp.next;
    //          2）再断链子：即将当前遍历的节点的next链接到要插入的节点，temp.next=heronode;

    public void addByOrder(HeroNode heronode){
        //因为头结点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为单链表，因为我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp=head;
        boolean flag=false;//flag标志添加的编号是否存在，默认为false

        while (true){
            if (temp.next==null){       //说明temp已经到链表的最后
                break;
            }
            if (temp.next.no>heronode.no){  //位置已经找到，就在temp后面插入
                break;
            }else if (temp.next.no==heronode.no){   //想插入的编号已经存在
                flag=true;  //编号存在
                break;
            }
            temp= temp.next;
        }
        if (flag==true){
            System.out.printf("准备插入的英雄编号%d已经存在，不能加入\n",heronode.no);
        }else {
            heronode.next = temp.next;
            temp.next = heronode;
        }
    }


    //显示链表[遍历]
    public void list(){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //头结点不能动，创建一个辅助变量来遍历
        HeroNode temp=head.next;
        while (true){
            //判断是否到了链表最后
            if (temp==null){
                break;
            }
            //输出节点信息
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
                '}';
    }
}
