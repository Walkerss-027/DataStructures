package com.atguigu.LinkedList;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建要给的链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();


        //加入表元素
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

//        //按编号加入表元素
//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.addByOrder(hero3);
//
//        //显示数据
//        singleLinkedList.list();

        //测试单链表反转
//        reversetList(singleLinkedList.getHead());
//        singleLinkedList.list();

        //测试逆序打印链表
        System.out.println("测试逆序打印单链表~");
        reversePrint(singleLinkedList.getHead());

//        //测试修改节点的代码
//        HeroNode newHeronode = new HeroNode(2, "小卢", "玉麒麟~");
//        singleLinkedList.update(newHeronode);
//
//        System.out.println("修改后的链表情况~~");
//        singleLinkedList.list();
//
//        //删除一个节点
//        singleLinkedList.del(1);
//        singleLinkedList.del(4);
//        singleLinkedList.del(2);
//        singleLinkedList.del(3);
//        System.out.println("删除后的链表情况~~");
//        singleLinkedList.list();

    }


    //将单链表反转(静态方法main可以直接调用不用创建对象)
    //思路：
    //1.判断链表是否为空或者只有一个节点，无需反转，直接返回
    //2.定义两个辅助变量（指针）cur，next，帮助遍历链表
    //3.从头到尾遍历链表，每遍历一个节点就将其取出，并放在新的链表 reverseHead（新的链表头节点）的最前端
    public static void reversetList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        //定义一个辅助指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表
        //从头到尾遍历原来链表，每遍历一个节点，就将其取出，并放在新的链表 reverseHead的最前端
        while (cur != null) {
            next = cur.next;  //暂时保存当前节点的下一个节点，之后会使用
            cur.next = reverseHead.next;  //将cur的下一个节点指向新的链表的最前端，如果没有保存next，此时cur在原链表后节点找不到，因为已经改变了cur
            reverseHead.next = cur;   //将cur连接到新的链表上
            cur = next;
        }
        head.next = reverseHead.next;

    }


    //逆序打印
    //利用栈，实现逆序打印
    //思路：
    //1.先判断是否为空链表
    //2.创建一个辅助变量和一个空栈并将遍历的节点放入栈内
    //3.出栈并打印
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        //创建一个空栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;   //cur后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印，pop 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }


}


//定义 SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点，头结点不要动
    private HeroNode head = new HeroNode(0, "", "");


    //   添加节点到单向链表
//    思路，当不考虑编号顺序时
//    1.找到当前链表的最后节点
//    2.将最后这个节点的next 指向新节点
    public void add(HeroNode heroNode) {

        //因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;
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
        //将最后这个节点的next 指向新的节点
        temp.next = heroNode;
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

    public void addByOrder(HeroNode heronode) {
        //因为头结点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为单链表，因为我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//flag标志添加的编号是否存在，默认为false

        while (true) {
            if (temp.next == null) {       //说明temp已经到链表的最后
                break;
            }
            if (temp.next.no > heronode.no) {  //位置已经找到，就在temp后面插入
                break;
            } else if (temp.next.no == heronode.no) {   //想插入的编号已经存在
                flag = true;  //编号存在
                break;
            }
            temp = temp.next;
        }
        if (flag == true) {
            System.out.printf("准备插入的英雄编号%d已经存在，不能加入\n", heronode.no);
        } else {
            heronode.next = temp.next;
            temp.next = heronode;
        }
    }


    //修改节点的信息，根据 no编号来修改，即 no编号不能改。
    //说明
    //1.根据newHeroNode 的 no来修改即可
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据 no编号
        //定义一个辅助变量
        HeroNode temp = head;
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
    //思路
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false; //标志是否找到待删除节点
        while (true) {
            if (temp.next == null) {   //已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
                //找到的待删除结点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; //temp后移，遍历
        }
        //判断flag
        if (flag) {  //找到
            //删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d 节点不存在\n", no);
        }
    }

    public HeroNode getHead() {
        if (head == null) ;
        return head;
    }

    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //头结点不能动，创建一个辅助变量来遍历
        HeroNode temp = head.next;
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

}


//定义 HeroNode ,每个 HeroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname) {
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
