package com.atguigu.Algorithm.Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    /*
    贪心算法
    经典应用场景：广播覆盖问题
     */
    public static void main(String[] args) {
        //创建广播电台，放入Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //将各个电台放入到 broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet1.add("广州");
        hashSet1.add("北京");
        hashSet1.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet1.add("成都");
        hashSet1.add("上海");
        hashSet1.add("杭州");

        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet1.add("杭州");
        hashSet1.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建ArrayList,存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //tempSet 存放遍历过程中电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();
        HashSet<String > fixSet = new HashSet<String>();

        //定义给maxKey,保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        //如果 maxKey 不为 null ，则会加入到 selects
        String maxKey = null;

        while (allAreas.size() != 0) {    //如果 allAreas 不为0，则表示还没有覆盖到所有的地区

            maxKey = null;    //如果直接在 while 中定义 maxKey 每次循环会床架一个 String 对象，浪费栈空间

            //遍历 broadcasts,取出对应的 key
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                fixSet.clear();

                HashSet<String> areas = broadcasts.get(key);    //当前key能够覆盖的地区
                tempSet.addAll(areas);

                //求 tempSet 和 allAreas 的交集，赋值给 tempSet
                tempSet.retainAll(allAreas);
                fixSet=broadcasts.get(maxKey);
                fixSet.retainAll(allAreas);

                //如果 tempSet 包含的地区比 maxKey 覆盖的地区多，就重置 maxKey
                //tempSet.size() > broadcasts.get(maxKey).size() 体现出贪婪算法的特点：每次都取最好
                if (tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > fixSet.size())) {
                    maxKey = key;

                }
            }

            //maxKey != null,就应该将 maxkey 加入到 selects
            if (maxKey != null) {
                selects.add(maxKey);
                //把 maxKey 指向的广播电台覆盖地区从 allAreas 去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }


    }
}
