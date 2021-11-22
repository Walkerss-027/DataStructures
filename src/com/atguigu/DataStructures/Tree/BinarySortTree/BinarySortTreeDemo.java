package com.atguigu.DataStructures.Tree.BinarySortTree;

import java.util.Arrays;

public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();

        //循环添加结点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
//            System.out.println(arr[i]);
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();

        //测试删除叶子结点
//        binarySortTree.delNode(2);
//        binarySortTree.delNode(5);
//        binarySortTree.delNode(9);
        binarySortTree.delNode(1);
        System.out.println("删除节点后");
        binarySortTree.infixOrder();

    }
}


//创建二叉排序树
class BinarySortTree {
    private Node root;

    //添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找要删除结点父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //删除右子树最小节点
    /**
     *功能：删除以右子结点为根结点的树的最小值结点，并返回其最小值
     * @param node  传入的结点
     * @return      返回以 node 为根结点的二叉树的最小节点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        while (target.left != null) {   //找到最左结点
            target=target.left;
        }
        delNode(target.value);
        return target.value;
    }

    //删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = search(value);    //找要删除的结点
            if (targetNode == null) {    //没找到要删除的结点
                return;
            }

            //如果我们发现待删除点没有父结点（当前二叉排序树只有一个结点）
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            //找父结点
            Node parent = searchParent(value);

            if (targetNode.left == null && targetNode.right == null) {   //如果要删除的结点是叶子结点
                //判断 targetNode 是父结点的左子结点还是右子结点
                if (parent.left != null && parent.left.value == value) { //要删的点是左子结点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {   //要删的点是右子结点
                    parent.right = null;
                }
            }else if (targetNode.left != null && targetNode.right != null){     //删除两棵子树的结点(用右子树最小节点取代掉此结点，保持二叉顺序树顺序不变)
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            }else {     //删除只有一棵子树的结点
                if (targetNode.left != null){   //待删除结点有左子结点
                    if (parent != null){
                        if (parent.left.value == value){    //待删除结点是父结点的左子结点
                            parent.left = targetNode.left;
                        }else {                             //待删除结点是父结点的右子结点
                            parent.right = targetNode.left;
                        }
                    }else {
                        root =targetNode.left;
                    }
                }else { //待删除结点有右子结点
                    if (parent != null){
                        if (parent.left.value == value){    //待删除结点是父结点的左子结点
                            parent.left = targetNode.right;
                        }else {                             //待删除结点是父结点的右子结点
                            parent.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }
                }
            }

        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }
}


//创建 Node 结点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加节点方法    （递归方式添加，需要满足二叉排序树的要求）
    public void add(Node node) {
        if (node == null) {
            return;
        }

        //判断当前传入的值，和当前子树的根结点的值的关系
        if (node.value < this.value) {
            if (this.left == null) {     //当前结点左子结点为null
                this.left = node;
            } else {     //递归的向左子树添加
                this.left.add(node);
            }
        } else {     //添加的节点的值大于等于当前节点值
            if (this.right == null) {    //右子结点为空
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

    }

    //查找要删除的结点

    /**
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {   //找到的就是该结点
            return this;
        } else if (value < this.value) {    //查找的结点小于当前结点，向左子树递归查找
            if (this.left == null) {     //如果左子结点为空
                return null;
            }
            return this.left.search(value);
        } else {     //查找的节点的值大于等于当前结点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除结点的父结点

    /**
     * @param value 要找到的结点的值
     * @return 返回的是要删除的结点父结点，如果没有就返回 null
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||  //如果当前结点就是要删除的结点的父结点，就返回
                (this.right != null && this.right.value == value)) {
            return this;
        } else {     //如果查找的值小于当前结点的值，并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);     //向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);    //向右子树递归查找
            } else {
                return null;    //没有找到父结点
            }
        }
    }

    //中序遍历二叉树
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

}
