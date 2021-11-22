package com.atguigu.DataStructures.Tree.AVLTree;

public class AVLTreeDemo {

    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("在没有平衡前");
        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight());
    }

}


//创建AVLTree
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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
     * 功能：删除以右子结点为根结点的树的最小值结点，并返回其最小值
     *
     * @param node 传入的结点
     * @return 返回以 node 为根结点的二叉树的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {   //找到最左结点
            target = target.left;
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
            } else if (targetNode.left != null && targetNode.right != null) {     //删除两棵子树的结点(用右子树最小节点取代掉此结点，保持二叉顺序树顺序不变)
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {     //删除只有一棵子树的结点
                if (targetNode.left != null) {   //待删除结点有左子结点
                    if (parent != null) {
                        if (parent.left.value == value) {    //待删除结点是父结点的左子结点
                            parent.left = targetNode.left;
                        } else {                             //待删除结点是父结点的右子结点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else { //待删除结点有右子结点
                    if (parent != null) {
                        if (parent.left.value == value) {    //待删除结点是父结点的左子结点
                            parent.left = targetNode.right;
                        } else {                             //待删除结点是父结点的右子结点
                            parent.right = targetNode.right;
                        }
                    } else {
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


//创造 Node 结点
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

        //当添加完一个节点后，如果：（右子树高度-左子树高度）> 1,左旋转
        if (this.rightHeight() - this.leftHeight() > 1) {
            //如果当前结点（根结点）右子结点（不为空）的左子树高度大于右子树高度
            if (this.right != null && this.right.leftHeight() > this.right.leftHeight()) {
                //先对右子结点进行右旋转
                this.right.rightRotate();
                //然后对当前结点进行左旋转
                leftRotate();
            } else {
                leftRotate();
            }
            return;     // ！！！ 必须添加 不然会继续向下执行下一个 if
        }

        //当添加完一个节点后，如果：（左子树高度-右子树高度）> 1,右旋转
        if (this.leftHeight() - this.rightHeight() > 1) {
            //如果当前结点（根结点）左子结点(不为空)的右子树高度大于左子树高度
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                //先对其左子结点进行左旋转
                this.left.leftRotate();
                //然后对当前结点进行右旋转
                rightRotate();
            } else {
                //直接进行右旋转
                rightRotate();
            }
            return;
        }

    }

    //当前节点的高度（以该结点为根结点的书的高度）
    public int height() {
        //left == null ? 0 : left.height() 解释 如果left == null 返回 0 ，否则执行 left.height() 递归
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //左旋转方法
    private void leftRotate() {
        //创建新的结点
        Node newNode = new Node(value);
        //把新节点的左子树设置成当前结点的左子树
        newNode.left = left;
        //新节点右子树设置为右子树的左子树
        newNode.right = right.left;       //如果this 的右子结点没有左子结点则指向 null
        //把当前结点的值替换成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成当前节点的右子树的右子树
        right = right.right;
        //把当前结点左子树设置成新节点
        left = newNode;
    }

    //右旋转方法
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;   //根结点位置没变，知识改变了根结点的值
        left = left.left;
        right = newNode;
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