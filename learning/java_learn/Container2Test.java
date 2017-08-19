package AlgorithmTraining.learning.java_learn;

import java.util.*;

/**
 * Created by 903 on 2017/8/7.
 */
public class Container2Test {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<>();
        //linkedHashMap本质上是一个HashMap 然后外加一个LinkedList维护插入顺序 这个是通过在器Entry上添加两个域(before after)
        //来实现双向链表的
        HashMap<Integer, String> map = new HashMap<>();
        linkedMap.put(10000, "A");
        linkedMap.put(10, "B");
        linkedMap.put(100, "C");
        linkedMap.put(1000, "D");
        map.put(10000, "A");
        map.put(10, "B");
        map.put(100, "C");
        map.put(1000, "D");
        System.out.println(map.keySet()); //keyset是无序的 所以无法实现 按照put的顺序去迭代
        System.out.println(linkedMap.keySet()); //由于linkedHashMap实现了 双向链表， 所以他的keyset是按照插入顺序来的
    }
}
