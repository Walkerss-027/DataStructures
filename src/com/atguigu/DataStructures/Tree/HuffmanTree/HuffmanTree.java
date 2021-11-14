package com.atguigu.DataStructures.Tree.HuffmanTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    /*
    赫夫曼树：带权路径（wpl）最小的树，也称最优二叉树，权值较大的结点离根结点较近
    带权路径长度：从根结点到该结点之间的路径长度与该结点的权的乘积
    wpl：所有叶子结点的带权路径长度之和
     */
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);

        //测试
        preOrder(root);
    }


    //编写一个前序遍历的方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("是空树，不能遍历");
        }
    }

    //创建赫夫曼树得方法

    /**
     * @param arr 需要创建成赫夫曼树的数组
     * @return 创建好后的赫夫曼树的 root 结点
     */
    public static Node createHuffmanTree(int[] arr) {
        /*
        第一步为了操作方便
        1.遍历 arr 数组
        2.将 arr 得每个元素构成一个 Node
        3.将 Node 放入到 ArrayList中(方便对 node 直接排序，直接用集合操作)
         */
        List<Node> nodes = new ArrayList<Node>();   //ArrayList 中的元素实际上时对象
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            //排序 从小到大排序
            Collections.sort(nodes);

            System.out.println("nodes=" + nodes);

            //取出根结点权值最小得两棵二叉树
            //(1)取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
            //(2)取出权值第二小的结点（二叉树）
            Node rightNode = nodes.get(1);

            //(3)构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //(4)从 ArrayList 删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //(5)将 parent 加入到 nodes
            nodes.add(parent);
        }
        //返回霍夫曼树的 root 结点
        return nodes.get(0);

    }
}


//创建节点类
//为了让Node 对象持续排列 Collectios 集合排序 让 Node 实现 Comparable 接口
class Node implements Comparable<Node> {
    int value;  //结点权值
    Node left;  //指向左子结点
    Node right; //指向右子结点

    //写一个前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排（如果要从大到小排序加 - ）
        return this.value - o.value;
    }
}
