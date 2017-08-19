package AlgorithmTraining.learning.java_learn;

/**
 * Created by BUPT_SS4G on 2017/8/14.
 */

import java.util.*;

public class ContainerTest2 {
    public static void main(String[] args) {
        Random r = new Random();
        List<Integer> arr = new ArrayList<>();
        TreeSet<Integer> ts = new TreeSet<>();
        HashSet<Integer> hs = new HashSet<>();
        LinkedHashSet<Integer> ls = new LinkedHashSet<>();
        LinkedHashMap<Integer, String> lm = new LinkedHashMap<>(128, .75f, true); //
        //TreeMap<Integer, String> tm = new TreeMap<>();
        for (int i = 0; i < 20; i++) {
            arr.add(r.nextInt(100));
        }
        ts.addAll(arr);
        hs.addAll(arr);
        ls.addAll(arr);
        System.out.println("instert order:" + arr);
        System.out.println("treeSet:" + ts); //将被插入的元素按照升序排序
        System.out.println("hashSet:" + hs); //顺序不确定
        System.out.println("linkedHashSet:" + ls); //按照插入的顺序
        arr = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            arr.add(i);
        }
        for (Integer i : arr) {
            lm.put(i, Integer.toHexString(i));
        }
        System.out.println(lm);
        lm.get(1);
        lm.get(2);
        lm.get(3);
        lm.get(4);
        lm.get(2);
        lm.get(2);
        lm.get(1);
        lm.get(3);
        System.out.println(lm); // 键0被放在了最前面 使用了LRU算法 最近访问最少的元素被放在前面

        BitSet bs = new BitSet(); //只用来记录布尔值
        bs.set(1, true);
        bs.set(2, false);
        bs.set(3, true);
        bs.flip(3);

        System.out.println(bs); //只返回真值
    }
}
