package com.atguigu.DataStructures.Tree.ThreadedBinaryTree;

public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        /*
        普通二叉树以某种次序遍历使其成为线索二叉树的过程叫做线索化。
        线索：指向前驱和后继结点的指针叫线索
        线索化的具体实现就是将二叉树以不同方式遍历。
        有 n 个结点的二叉树有 n+1 个链域是空的 [ 2n - ( n -1 ) = n + 1] ,我们可以利用剩下的 n + 1 个空域来存放
        遍历过程中结点的前驱和后继信息。
         */
        //测试中序线索二叉树
        HeroNode root = new HeroNode(1,"tom");
        HeroNode node2 = new HeroNode(3,"jack");
        HeroNode node3 = new HeroNode(6,"smith");
        HeroNode node4 = new HeroNode(8,"mary");
        HeroNode node5 = new HeroNode(10,"king");
        HeroNode node6 = new HeroNode(14,"dim");

        //手动连接成二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试10号结点
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号结点的前驱结点是="+leftNode);
        System.out.println("10号结点的后继结点是="+rightNode);
    }

}


//定义ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;

    //为了实现线索化，需要创建一个指向当前结点前驱结点的指针
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载 threadedNodes 方法
    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //遍历线索化二叉树
    public void threadedList(){
        //定义一个变量，存储当前遍历的结点，从root 开始
        HeroNode node = root;
        while (node != null){
            /*
            循环的找到 leftType == 1 的结点，第一个找到的就是8结点
            后面随着遍历而变化，因为当 leftType == 1时，说明该结点是按照线索化处理的有效节点
             */
            while (root.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前这个结点
            System.out.println(node);
            while (node.getRightType() == 1){   //右子结点是后继结点
                //获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node = node.getRight();
        }
    }


    //编写对二叉树进行中序线索化的方法
    /**
     *
     * @param node  就是当前需要线索化的结点
     */
    public void threadedNodes(HeroNode node) {

        //如果 node == null ，不能线索化
        if (node == null) {
            return;
        }

        /*
        线索化思路：
        1.先线索化左子树
        2.线索化当前结点[难点]
            1)先处理当前结点的前驱结点
            2)处理后继结点
        3.最后线索化右子树
         */
        threadedNodes(node.getLeft());

        if (node.getLeft() == null) {   //当前结点没有左子结点
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点左指针类型，指向前驱结点
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);     //让前驱结点的右指针指向当前结点
            pre.setRightType(1);    //修改前驱结点的右指针类型
        }
        // ！！！ 每处理一个结点，让当前结点是下一个结点的前驱结点
        pre=node;

        threadedNodes(node.getRight());


    }


    //删除结点
    public void delNode(int no) {
        if (root != null) {
            //如果只有一个root结点，判断 root 是否是待删除结点
            if (root.getNo() == no) {
                root.delNode(no);
            } else {
                root.delNode(no);   //递归删除
            }

        } else {
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


//创建HeroNode
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    /*
    说明：
    相当于给结点左右增加了两个标志位置，指向的是下一个结点是0，指向的是前驱后继结点则为1
    1.如果 leftType == 0 表示指向的是左子结点，如果 1 则表示指向前驱结点
    2.如果 rightType == 0 表示指向的是右子结点，如果 1 则表示指向后继结点
     */
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

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