package AlgorithmTraining.learning.java_learn;

/**
 * Created by g55 on 2017/6/1.
 */
import java.util.*;
class ListTest {
    public static void main(String[] args) {
        String[] sArr = {"Abc", "Def", "Ghi", "Jkl"};
        List<String> fakeArrayList = Arrays.asList(sArr); // 这个arrayList不是真正的ArrayList
        //会将sArr对象 赋值给一个不可变的 内部数组引用 所以fakeArrayList的内部数组对象没有变
        for (int i = 0; i < fakeArrayList.size(); i++)
            System.out.println("Element "+i+":"+fakeArrayList.get(i));
            //因为内部数组对象不可变 所以只能进行读取操作
        // fakeArrayList.add("Mno") // 会引发 不支持操作的异常因为Arrays.asList没有实现这个方法

        System.out.println("Show ArraysList trueArrayList");
        List<String> trueArrayList = new ArrayList<String>(10); // 这个是真正的arrayList
        //ArrayList 可以指定一个初始的空间来减少以后扩展的时间 可以提高效率
        //查看ArrayList的实现可以发现 其底层存储对象的东西其实是java的原生数组对象
        // 因为是原生的所以查找 修改的效率很高 但是 插入 删除的效率很低 因为这会涉及
        // 到数组中元素的拷贝 所以使用ArrayList 尽量不要多次执行插入删除等操作

        trueArrayList.add("start");
        trueArrayList.addAll(fakeArrayList); // 类似于Python extend的 作用
        Collections.addAll(trueArrayList, "A", "B", "C");
        //collections.addAll 方法可以将一个可以变参数列表添加到一个Collections里面
        //foreach 以及可变参数 其实都只是语法糖 也就是把一般比较不方便的东西 通过编译器
        //提供一个简写的方式给用户使用 编译器帮助用户去将语法糖翻译成原生的代码的样子
        for (String s : trueArrayList)
            System.out.println(s);

        System.out.println("Show LinkedList linkList0");
        List<String> linkList0 = new LinkedList<String>();
        linkList0.add("start");
        linkList0.addAll(fakeArrayList);
        //类似于Python extend的 作用
        //LinkedList 的底层实现是一个双链表 使用链表的插入和删除功能会很快
        //但是使用查找会沿着链表一直找下去会很慢 真正的库实现
        //是判断index在链表的前半部还是后半部 这样就可以
        //确定是从头开始查找还是从尾部开始向前查找

        System.out.println("befor remove element 0 "+linkList0);
        linkList0.remove(0);
        System.out.println("after remove element 0 "+linkList0);

        //总结 asList产生的对象 只能保持原有数组的长度 不能进行增加， 删除等试图改变原有数组长度的操作
        //ArrayList 的底层实现是 数组 所以查找高效 但是如果进行改变长度的 操作会导致创建新的数组
        //并且发生内容的拷贝 导致效率降低
        //LinkedList 的底层实现是双向链表，所以其 能够更好的实现插入和删除的操作 但是查找会变得较为麻烦 查找的复杂度为O(n)
    }
}

class StackTest {
    private static class Stack<E> {
        private LinkedList<E> storage = new LinkedList<E>();
        public void push(E e) {storage.addFirst(e);}
        public E peek() {return storage.getFirst();}
        public boolean isEmpty() {return storage.isEmpty();}
        public E pop() {return storage.removeFirst();}
        public String toString() {return storage.toString();}
    }
    public static void main(String[] args) {
        Stack<Integer> stack0 = new Stack<Integer>();
        for (int i = 0; i < 5; i++)
            stack0.push(i);
        System.out.println(stack0);
        System.out.println(stack0.pop());
        System.out.println(stack0);
        System.out.println(stack0.peek());
        System.out.println(stack0);
    }
}

class MapTest {
    public static void main(String[] args) {
        Map<String, Integer> treeMap0 = new TreeMap<String, Integer>();
        Map<String, Integer> hashMap0 = new HashMap<String, Integer>();
        String[] keys = {"ABC", "DEF", "GHI", "JKL"};
        Integer[] vals = {5, 6, 7, 8};
        for (int p = 0; p < keys.length; p++) {
            treeMap0.put(keys[p], vals[p]);
            hashMap0.put(keys[p], vals[p]);
        }
        System.out.println("treeMap0:"+treeMap0);
        System.out.println("hashMap0:"+treeMap0);

        System.out.println("get value of key DEF in treeMap:"+treeMap0.get("DEF"));
        System.out.println("get value of key DEF in hashMap:"+hashMap0.get("DEF"));

        treeMap0.put("DEF", treeMap0.get("DEF")+10); //需要修改原来的值 需要把原来的值先读取出来
        hashMap0.put("DEF", hashMap0.get("DEF")+10);

        System.out.println("new value of key DEF in treeMap:"+treeMap0.get("DEF"));
        System.out.println("new value of key DEF in hashMap:"+hashMap0.get("DEF"));

        System.out.println("treemap has key ABC? "+treeMap0.containsKey("ABC"));
        System.out.println("hashmap has key ABC? "+hashMap0.containsKey("ABC"));

        System.out.println("treemap has value ABC? "+treeMap0.containsValue(5));
        System.out.println("hashmap has value ABC? "+hashMap0.containsValue(20));

        //总结两种map的实现结构不一样所以他们的 也不一样
        //TreeMap 底层实现是红黑树 其查找和修改的复杂度均为O(lgN)
        //HashMap 的底层实现是哈希表 查找和修改的复杂度理想情况下都为O(1)
        //但是会浪费一些空间 会随着元素的插入发生访问变慢的情况
        Map<String, Integer> treeMap1 = new TreeMap<String, Integer>();
        Map<String, Integer> hashMap1 = new HashMap<String, Integer>();

        treeMap1.put("C", 3);
        treeMap1.put("E", 5);
        treeMap1.put("A", 1);
        treeMap1.put("D", 4);
        treeMap1.put("B", 2);
        hashMap1.put("C", 3);
        hashMap1.put("E", 5);
        hashMap1.put("A", 1);
        hashMap1.put("D", 4);
        hashMap1.put("B", 2);

        System.out.println(treeMap1.keySet());
        System.out.println(hashMap1.keySet());
    }
}

class SetTest {
    public static void main(String[] args) {
        int[] elements = {1, 1, 9 ,8, 7, 1, 101, 11};
        Set<Integer> hashSet0 = new HashSet<Integer>();
        for (int i: elements)
            System.out.println("add value to hashSet0:"+i+hashSet0.add(i)); //已经存在于set中的值 add函数会返回一个false
        System.out.println("hashSet0:"+hashSet0);
        Set<Integer> treeSet0 = new TreeSet<Integer>();
        for (int i: elements)
            System.out.println("add value to treeSet0:"+i+treeSet0.add(i)); //已经存在于set中的值 add函数会返回一个false
        System.out.println("treeSet0:"+treeSet0); //可以看出TreeSet是排过序的 因为是 红黑树实现
    }
}

class QueueTest {
    public static void main(String[] args) {
        Queue<String> queue0 = new LinkedList<String>(); //queue 只是一个接口 具体的对象需要使用LinkedList
        String[] vals = {"A", "B", "C", "D"};
        for (String s: vals)
            queue0.offer(s);
        System.out.println(queue0);
        System.out.println(queue0.peek()); // 输出A 但是不会删除 A
        System.out.println(queue0.poll()); // 弹出A
        System.out.println(queue0.poll()); // 弹出B
        System.out.println(queue0.poll()); // 弹出C
        System.out.println(queue0.poll()); // 弹出D
        System.out.println(queue0.poll()); // 队列为空 返回null
        System.out.println(queue0.poll()); // 队列为空 返回
    }
}

class PiorityQueueTest {
    private static class MyElement implements Comparable<MyElement>{
        int i;
        String s;
        MyElement(int i, String s) {
            this.i = i;
            this.s = s;
        }
        @Override
        public int compareTo(MyElement o2) {
            if (this.i > o2.i)
                return -1;
            else if (this.i == o2.i) {
                if (this.s.charAt(0) > o2.s.charAt(0))
                    return 1;
                else if (this.s.charAt(0) > o2.s.charAt(0))
                    return 0;
                else
                    return -1;
            }
            else
                return 1;
        }
        @Override
        public String toString() {
            return this.i+":"+this.s;
        }
    }
    public static void main(String[] args) {
        PriorityQueue<MyElement> pq = new PriorityQueue<MyElement>();
        String[] sList = {"AHJ", "KKSJ", "UUU", "sfds"};
        int[] iList = {1, 2, 5, 4};
        MyElement[] me = new MyElement[sList.length];
        for (int i = 0; i < iList.length; i++)
            me[i] = new MyElement(iList[i], sList[i]);
        for (int i = 0; i < me.length; i++)
            pq.offer(me[i]);
        System.out.println(pq); //output: [5:UUU, 4:sfds, 2:KKSJ, 1:AHJ]
        //可以看出 priority queue 是在插入时就已经进行了排序 排序的顺序与具体的comparable接口实现有关
        for (int i = 0; i < me.length; i++)
            System.out.println(pq.poll());
    }
}

class IteratorTest {
    public static void main(String[] args) {
        Iterator<String> mIt0 = new MyIterator("  song.zi.heng.com.cn ").iterator();
        while (mIt0.hasNext()) { //hasNext() 方法是Iterator的 Iterator 是不能放在foreach中的
            System.out.println(mIt0.next());
        }

        MyIterator mIt = new MyIterator("  song.zi.heng.com.cn ");
        for (String s : mIt) {  // foreach 中放的应当是一个Iterable的类 foreach 会自动调用该类的Iterable方法
            System.out.println(s);
        }
    }
}

class MyIterator implements Iterable<String> {
    String[] it;
    public MyIterator(String str) {
        it = str.trim().split("\\.");
    }
    public Iterator<String> iterator() { //实现Iterable的获取Iterator的方法
        return new Iterator<String>() { // 创建一个迭代器内部匿名类
            int index = 0;
            @Override
            public String next() {
                index++;
                return it[index-1];
            }
            @Override
            public boolean hasNext() {
                return index < it.length;
            }
        };
    }
}

public class ContainerTest {
    public static void main(String[] args) {
        System.out.println("Fin");
        IteratorTest.main(null);
    }
}
