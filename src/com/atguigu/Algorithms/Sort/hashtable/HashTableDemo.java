package com.atguigu.Algorithms.Sort.hashtable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {

        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                default:
                    break;
            }
        }
    }


    //创建HashTab 管理多条链表
    static class HashTab {
        private EmpLinkedList[] empLinkedListsArray;
        private int size;

        //构造器
        public HashTab(int size) {
            this.size = size;
            //初始化 empLinkedListsArray
            empLinkedListsArray = new EmpLinkedList[size];
            //?留坑,这时不要忘了分别初始化每个链表
            for (int i = 0; i < size; i++) {
                empLinkedListsArray[i] = new EmpLinkedList();
            }
        }

        //添加雇员
        public void add(Emp emp) {
            //根据员工id ，得到该员工应该添加到哪条链表
            int empLinkedListNO = hashFun(emp.id);
            //将 emp 添加到对应的链表中
            empLinkedListsArray[empLinkedListNO].add(emp);

        }

        //遍历所有的链表
        public void list() {
            for (int i = 0; i < size; i++) {
                empLinkedListsArray[i].list(i);
            }
        }

        //根据输入id，查找雇员
        public void findEmpById(int id){
            //使用散列函数确定到哪条链表查找
            int empLinkedListNO = hashFun(id);
            Emp emp=empLinkedListsArray[empLinkedListNO].findEmpById(id);
            if (emp!=null){ //找到
                System.out.printf("在第%d条链表中找到雇员 id = %d\n",(empLinkedListNO+1),id);
            }else {
                System.out.println("在哈希表中，没有找到该雇员");
            }

        }

        //编写散列函数,使用取模法
        public int hashFun(int id) {
            return id % size;
        }
    }


    //表示一个雇员
    static class Emp {
        public int id;
        public String name;
        public Emp next;

        public Emp(int id, String name) {
            super();
            this.id = id;
            this.name = name;
        }
    }


    //创建 EmpLinkedList ,表示链表
    static class EmpLinkedList {
        //头指针，执行第一个Emp，因此我们这个链表的 head 是直接指向第一个 Emp
        private Emp head;

        /*
        添加雇员

        说明：
        1.假定，当添加雇员时， id 是自增长，即 id 的分配是从小到大
        只需将雇员直接加到最后即可
         */
        public void add(Emp emp) {
            //如果是添加第一个雇员
            if (head == null) {
                head = emp;
                return;
            }

            //如果不是第一个雇员，则用辅助指针定位到最后
            Emp curEmp = head;

            while (true) {  //while 退出时即为最后一个节点
                if (curEmp.next == null) { //到链表最后了
                    break;
                }
                curEmp = curEmp.next; //后移
            }
            curEmp.next = emp;
        }

        //遍历链表雇员信息
        public void list(int no) {
            if (head == null) {    //链表是否为空
                System.out.println("第" + (no+1) + "链表为空");
                return;
            }
            System.out.print("第" + (no+1) + "链表的信息为");
            Emp curEmp = head;    //辅助指针
            while (true) {
                System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
                if (curEmp.next == null) { //curEmp 已为最后节点
                    break;
                }
                curEmp = curEmp.next; //后移
            }
            System.out.println();
        }

        //根据id查找雇员
        //查找到返回Emp，没有查找到返回null
        public Emp findEmpById(int id){
            //判断链表是否为空
            if (head==null){
                System.out.println("链表为空");
                return null;
            }

            Emp curEmp=head;    //定义辅助指针
            while (true){
                if (curEmp.id==id){ //找到
                    break;
                }
                if (curEmp.next==null){ //说明遍历当前链表没有找到该雇员
                    curEmp=null;
                    break;
                }
                curEmp=curEmp.next;
            }

            return curEmp;
        }

    }
}
