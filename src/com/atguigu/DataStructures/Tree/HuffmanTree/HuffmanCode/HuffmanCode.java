package com.atguigu.DataStructures.Tree.HuffmanTree.HuffmanCode;

import java.util.*;

public class HuffmanCode {
    /*
    根据赫夫曼编码压缩数据的原理，需要创建“i like like like java do you like a java”对应的赫夫曼树
    思路：
    1.Node {data(存放数据),weight(权值),left 和 right}
    2.得到“i like like like java do you like a java”对应的 byte[]数组
    3.编写一个方法，将准备构建赫夫曼树的 Node 结点放到 List,形式[Node[date=97,weight=5],Node[data=32,weight=9]...]
    体现 d:1 y:1 u:1 j:2 v:2 o:2 l:4 k:4 e:4 i:5 a:5  :9
    4.可以通过List 创建对应的赫夫曼树
     */
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();   //getBytes()返回的是每个字符的 ASCII 码
        System.out.println(Arrays.toString(contentBytes));  //如果直接打印数组得到的是它的内存地址 B@16b98e56
        System.out.println(contentBytes.length);    //40

        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes=" + nodes);   //data 是 ASCII 码

        //测试创建的二叉树
        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //前序遍历
        preorder(huffmanTreeRoot);

    }

    //前序遍历方法
    private static void preorder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("赫夫曼树为空");
        }
    }

    /**
     *
     * @param bytes 接收字节数组
     * @return  返回的就是 List 形式 [Node[date=97,weight=5],Node[data=32,weight=9]...]
     */
    private static List<Node> getNodes(byte[] bytes) {

        //1.创建一个 ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();

        //遍历 bytes ,统计每一个byte 出现的次数 -> map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();    //counts 里面键值对右式空
        for (byte b : bytes) {
            Integer count = counts.get(b);  //在 Map 中获得 b 的出现次数，如果没有这个字符（没有 b 这个key）count = null
            if (count == null) { //Map 还没有这个字符数据，第一次传入
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
//            counts.put(b, counts.getOrDefault(b, 0) + 1); //优雅
        }

        //把每个键值对转成一个Node 对象，并加入到 nodes 集合
        //遍历 Map
        /*
        Map.Entry 是 Map 声明的一个内部接口，此接口为泛型，定义为 Entry<K,V>。它表示 Map 中的一个实体（一个key-value对）。接口中有getKey(),getValue方法。
        EntrySet 是一个键值对集合，Entry 是一个键值对，都属于Map
         */

        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }


    //通过 List 创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes){

        while (nodes.size() > 1){
            //排序，从小到大
            Collections.sort(nodes);
            //取出第一棵最小二叉树
            Node leftNode = nodes.get(0);
            //取出第二棵最小二叉树
            Node rightNode = nodes.get(1);
            //创建一棵新的二叉树，它的结点没有 data，只有权值
            Node parent = new Node(null,leftNode.weight+rightNode.weight);
            parent.left=leftNode;
            parent.right=rightNode;

            //将已经处理的两棵二叉树从 nodes 删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将新的二叉树加入到 nodes
            nodes.add(parent);
        }
        //最后的节点，就是赫夫曼树的根结点
        return nodes.get(0);
    }


}


//创建 Node ,存放数据和权值
class Node implements Comparable<Node> {
    Byte data;  //存放数据（字符），比如 'a' => 97, ' ' => 32
    int weight; //权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

}
