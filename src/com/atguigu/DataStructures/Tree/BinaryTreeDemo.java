package com.atguigu.DataStructures.Tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点(手动创建)
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //将结点连接成树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //测试
        System.out.println("前序遍历");
        binaryTree.preOrder();

        //测试
        System.out.println("中序遍历");
        binaryTree.infixOrder();

        //测试
        System.out.println("后序遍历");
        binaryTree.postOrder();

//        //前序遍历查找
//        //前序遍历的次数：4
//        System.out.println("前序遍历方式~");
//        HeroNode resNode = binaryTree.preOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
//        } else {
//            System.out.printf("没有找到 no = %d 的英雄", 5);
//        }
//        System.out.println();
//
//        //中序遍历查找
//        //中序遍历的次数：3
//        System.out.println("中序遍历方式~");
//        resNode = binaryTree.infixOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
//        } else {
//            System.out.printf("没有找到 no = %d 的英雄", 5);
//        }
//        System.out.println();
//
//        //中序遍历查找
//        //中序遍历的次数：2
//        System.out.println("后序遍历方式~");
//        resNode = binaryTree.postOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
//        } else {
//            System.out.printf("没有找到 no = %d 的英雄", 5);
//        }


        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
        //binaryTree.delNode(5);
        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();




    }
}


//定义BinaryTree 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }


    //删除结点
    public void delNode(int no){
        if (root != null){
            //如果只有一个root结点，判断 root 是否是待删除结点
            if (root.getNo() == no){
                root.delNode(no);
            }else {
                root.delNode(no);   //递归删除
            }

        }else {
            System.out.println("空树，不能删除");
        }
    }


    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }


    //中序遍历
    /*
    思路：
    1.先判断左子结点是否为空，不为空，则递归中序查找
    2.如果左递归没找到，则和当前结点比较，如果是则返回当前结点，不是，则继续右递归中序查找
    3.右递归中序查找没找到则返回null
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }


    //后序遍历
    /*
    思路：
    1.判断左子结点是否为空，如果不为空则递归后序查找
    2.没有找到则判断右子结点是否为空，如果不为空，则右递归后序查找
    3.再和当前结点比较，是则返回，不是则返回null
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }


    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrdersearch(no);
        } else {
            return null;
        }
    }


    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }


    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

}

//先创建HeroNode结点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


    //递归删除结点
    /*
    1.如果是叶子结点，则删除该结点
    2.如果删除的结点是非叶子节点，则删除该子树

    思路：
    1.因为二叉树是单向的，所以我们判断当前结点的子结点是否需要删除结点，而不能去判断这个结点是不是需要删除的结点
    2.当前结点左子结点不为空，而且左子结点就是要删除结点，就将this.left = null ; 并返回（结束递归）
    3.当前节点右子结点不为空，而且右子结点就是要删除结点，就将this.right = null ; 并返回（结束递归）
    4.如果第2，3步没有删除结点，则需要向左子树递归删除
    5.如果第4步也没有删除结点，则要向右子树递归删除
     */
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        if (this.left != null) {
            this.left.delNode(no);
        }

        if (this.right != null) {
            this.right.delNode(no);
        }


    }


    //前序遍历
    public void preOrder() {
        System.out.println(this);   //先输出父结点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }


    //中序遍历
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父结点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }


    //后序遍历
    public void postOrder() {
        //递归向左子树遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归向右子树遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        //输出父结点
        System.out.println(this);
    }


    //前序遍历查找
    /*
    思路：
    1.判断当前结点 no 是否等于要查找的
    2.相等，返回当前结点
    3.不相等，判断左子结点是否为空，如果不为空则递归前序查找
    4.如果左递归前序查找找不到结点，则判断右子结点是否为空，如果不为空则继续向右递归前序查找
     */

    /**
     * @param no 查找 no
     * @return 找到返回 Node ，没找到返回null
     */
    public HeroNode preOrdersearch(int no) {
        if (this.no == no) { //当前结点是不是要找结点
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {    //判断左子结点是否为空,不为空则向左递归
            resNode = this.left.preOrdersearch(no);
        }
        if (resNode != null) {      //resNode 不为空则表示结点已找到
            return resNode;
        }
        if (this.right != null) {   //判断右子结点是否为空，不为空则向右递归
            resNode = this.right.preOrdersearch(no);
        }
        return resNode;
    }


    //中序遍历查找
    /*
    思路：
    1.判断当前结点左子结点是否为空，如果不为空，则递归中序查找
    2.如果没找到，就和当前结点比较，如果是，则返回当前结点，否则继续进行右递归的中序查找
    3.右递归中序查找，找到返回，否则返回null
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {    //找到了，找到之前 resNode一直为null
            return resNode;
        }

        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }


    //后序遍历查找
    /*
    思路
    1.先判断当前结点的左子结点是否为空，不为空则递归后续查找
    2.没找到则判断当前结点右子结点是否为空，如果不为空则右递归
    3.和当前结点比较，是，则返回，否则返回null
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}