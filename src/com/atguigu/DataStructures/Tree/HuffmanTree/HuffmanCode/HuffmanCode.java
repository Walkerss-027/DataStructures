package com.atguigu.DataStructures.Tree.HuffmanTree.HuffmanCode;

import java.io.*;
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

        byte[] huffmanCodesByte = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是：" + Arrays.toString(huffmanCodesByte) + "长度=" + huffmanCodesByte.length);

//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println("nodes=" + nodes);   //data 是 ASCII 码
//
//        //测试创建的二叉树
//        System.out.println("赫夫曼树");
//        Node huffmanTreeRoot = createHuffmanTree(nodes);
//        //前序遍历
//        preorder(huffmanTreeRoot);
//
//        //测试是否生成了赫夫曼编码
//        Map<Byte, String> huffmancodes = getcodes(huffmanTreeRoot);
//        System.out.println("生成的赫夫曼编码表" + huffmancodes);
//
//        byte[] huffmanCodeByte = zip(contentBytes, huffmancodes);
//        System.out.println("huffmanCodeByte=" + Arrays.toString(huffmanCodeByte));

    }


    //将一个文件压缩
    /**
     * @param srcFile 希望压缩未年检的全路径
     * @param dstFile 要锁后文件放在哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {

        FileInputStream is = null;  //创建输入流
        //创建文件的输出流
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {

            is = new FileInputStream(srcFile);  //创建文件的输入流
            //创建一个和源文件大小一样的 byte[]
            byte[] b = new byte[is.available()];    //available 可以得到整个文件的大小

            is.read(b); //读取文件

            byte[] huffmanBytes = huffmanZip(b);    //获取对应的霍夫曼编码表

            os = new FileOutputStream(dstFile);     //创建文件的输出流，存放压缩文件

            oos = new ObjectOutputStream(os);       //创建一个和文件输出流关联的 ObjectOutputStream

            oos.writeObject(huffmanBytes);      //把赫夫曼编码后的字节数组写入压缩文件
            //这里以对象流的方式写入赫夫曼编码，是为了以后我们恢复原文件时使用
            //注意一定要把赫夫曼编码写入压缩文件
            oos.writeObject(huffmancodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }


    }


    //将一个文件解压
    /**
     *
     * @param zipFile   准备解压的文件
     * @param dstFile   将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile,String dstFile){

        //定义文件输入流
        InputStream is =null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {

            is = new FileInputStream(zipFile);  //创建文件输入流

            ois = new ObjectInputStream(is);    //创建一个和 is 关联的对象输入流

            byte[] huffmanBytes = (byte[]) ois.readObject();    //读取byte数组 huffmanBytes

            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject(); //读取赫夫曼编码表

            byte[] bytes=decode(huffmanCodes,huffmanBytes); //解码

            os = new FileOutputStream(dstFile); //将 bytes 数组写入到目标文件

            os.write(bytes);        //写数据到 dstFile
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                ois.close();
                os.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


    //完成数据的解压
    /*
    思路：
    1.将 huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
      重写先转成赫夫曼编码对应的二进制的字符串110110110001101...
    2.赫夫曼编码对应的二进制的字符串”110110110001101...“ => 对照 赫夫曼编码 => "i like like like java do you like a java"
     */

    /**
     * 功能：完成对数据的解码
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1.先得到 huffmanBytes 对应的二进制的字符串，形式 110110110001101...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(flag, b));
        }
//        System.out.println("赫夫曼字节数组对应的二进制字符串="+stringBuilder.toString());

        //把字符串安装指定的赫夫曼编码进行转码
        //把赫夫曼编码进行调换，因为反向查询 a->100 100->a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建一个集合，存放 byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引，扫描 stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;    //小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //取出一个'1' '0'
                String key = stringBuilder.substring(i, i + count);  //i 不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) { //没有匹配到
                    count++;
                } else { //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count - 1;   //i 移动到count位置
        }
        //当for 循环结束后， list 存放了所有字符
        //把 list 中的数据放入到 byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    /**
     * 功能：将一个 byte 转成一个二进制的字符串
     *
     * @param b    //传入的 byte
     * @param flag //标志是否需要补高位，如果是最后一个字节，无需补高位
     * @return b对应的二进制的字符串（按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存 b
        int temp = b;   //将b 转成 int
        //如果是正数需要补高位
        if (flag) {
            temp |= 256;    //按位与 256 1 0000 0000 | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);    //返回的是 temp 对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }


    }


    //使用一个方法把之前的方法封装起来，便于调用
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据 nodes 创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //生成对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getcodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码，压缩，得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }


    /**
     * @param bytes        这是原始的字符串对应的 byte[]
     * @param huffmancodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的 byte[] 一串字符串
     */
    //编写一个方法，将字符串对应的 byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼码压缩后的 byte[]
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmancodes) {
        //1.利用 huffmanCodes 将 bytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历 bytes 数组
        for (byte b : bytes) {
            stringBuilder.append(huffmancodes.get(b));
        }

//        System.out.println("测试 stringBuilder=" + stringBuilder.toString());  //110110110001101...

        //将”110110110001101...“ 转成 byte[]

        //统计返回的 byte[] huffmancodes 长度（8位为一字节）
        int len = (stringBuilder.length() + 7) / 8;    // +7 是为了末尾不满足8的时候让商+1

        //创建压缩后的 byte 数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;    //记录是第几个 byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {  //因为是每 8 位对应一个 byte，所以步长+8
            String strByte;
            if (i + 8 > stringBuilder.length()) {   //不够 8 位
                strByte = stringBuilder.substring(i);   //返回 index = i 之后的字符串（把index=i之前的都切掉）
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将 strByte 转换成一个 byte 数组
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }


    //生成赫夫曼树对应的赫夫曼编码
    /*
    思路：
    1.将赫夫曼表放在 Map<Byte,String> 形式
        32->01 97->100 100->1100 等等[形式]
    2.在生成赫夫曼编码表示，需要去拼接路径，定义一个 StringBuilder 存储某个叶子结点的路径
     */
    static Map<Byte, String> huffmancodes = new HashMap<Byte, String>();
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，重载 getCodes
    public static Map<Byte, String> getcodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理 root 的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理 root 的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmancodes;
    }


    /**
     * 功能：将传入的 node 结点的所有叶子结点的赫夫曼编码得到，并放入 huffmanCodes 集合
     *
     * @param node          传入结点
     * @param code          路径：左子结点是0，右子结点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将 code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {  //node 非空
            if (node.data == null) { //node 是非叶子结点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else { //找到叶子结点最后
                huffmancodes.put(node.data, stringBuilder2.toString());
            }
        }
    }


    //前序遍历方法
    private static void preorder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }


    /**
     * 功能：把 Node 结点放入到 nodes 中
     *
     * @param bytes 接收字节数组
     * @return 返回的就是 List 形式 [Node[date=97,weight=5],Node[data=32,weight=9]...]
     */
    private static List<Node> getNodes(byte[] bytes) {

        //1.创建一个 ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();

        //遍历 bytes ,统计每一个byte 出现的次数 -> map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();    //counts 里面键值对右式空
        for (byte b : bytes) {
            Integer count = counts.get(b);  //在 Map 中获得 b 的出现次数，如果没有这个字符（没有 b 这个key）count = null
            if (count == null) { //Map 还没有这个字符数据，第一次传入
                counts.put(b, 1);   //就创建这个键值对
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
    private static Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            //排序，从小到大
            Collections.sort(nodes);
            //取出第一棵最小二叉树
            Node leftNode = nodes.get(0);
            //取出第二棵最小二叉树
            Node rightNode = nodes.get(1);
            //创建一棵新的二叉树，它的结点没有 data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

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
