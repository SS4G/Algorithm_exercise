package AlgorithmTraining.learning.java_learn;

/**
 * Created by g55 on 2017/6/1.
 */
import java.util.*;
class CollectionsUtils<E>{
    public void showCollections(Collection<E> c) {
        for (E c0 : c)
            System.out.println(c0);
    }
}
class ListTest {
    public static void main(String[] args) {
        String[] sArr = {"Abc", "Def", "Ghi", "Jkl"};
        List<String> sList = Arrays.asList(sArr);
        //会将sArr对象 赋值给一个不可变的 内部数组引用 所以sList的内部数组对象没有变
        for (int i = 0; i < sList.size(); i++)
            System.out.println("Element "+i+":"+sList.get(i));
            //因为内部数组对象不可变 所以只能进行读取操作
        // sList.add("Mno") // 会引发 不支持操作的异常因为Arrays.asList没有实现这个方法

        System.out.println("Show ArraysList sList0");
        List<String> sList0 = new ArrayList<String>(10);
        //ArrayList 可以指定一个初始的空间来减少以后扩展的时间 可以提高效率
        //查看ArrayList的实现可以发现 其底层存储对象的东西其实是java的原生数组对象
        // 因为是原生的所以查找 修改的效率很高 但是 插入 删除的效率很低 因为这会涉及
        // 到数组中元素的拷贝 所以使用ArrayList 尽量不要多次执行插入删除等操作

        sList0.add("start");
        sList0.addAll(sList); // 类似于Python extend的 作用
        Collections.addAll(sList0, "A", "B", "C");
        //collections.addAll 方法可以将一个可以变参数列表添加到一个Collections里面
        //foreach 以及可变参数 其实都只是语法糖 也就是把一般比较不方便的东西 通过编译器
        //提供一个简写的方式给用户使用 编译器帮助用户去将语法糖翻译成原生的代码的样子
        for (String s : sList0)
            System.out.println(s);

        System.out.println("Show LinkedList sList1");
        List<String> sList1 = new LinkedList<String>();
        sList1.add("start");
        sList1.addAll(sList);
        //类似于Python extend的 作用
        //LinkedList 的底层实现是一个双链表 使用链表的插入和删除功能会很快
        //但是使用查找会沿着链表一直找下去会很慢 真正的库实现
        //是判断index在链表的前半部还是后半部 这样就可以
        //确定是从头开始查找还是从尾部开始向前查找

        for (String s : sList1)
            System.out.println(s);
        sList1.remove()



    }
}

class StackTest {

}

class SetTest {

}

class QueueTest {

}

class PiorityQueueTest {

}

class MapTest {

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

class StringTest {
    public static void main(String[] args) {
        String s1 = "ABC"; // s1 s2 这些常量是放在 常量区的
        String s2 = "ABC";
        String s3 = new String("ABC"); // s3 使用new创建的是分配在堆区的
        System.out.println(s1 == s2); // true s1 s2 为常量被编译为 同一个常量对象
        System.out.println(s1 == s3); // false s1 s3 不是同一个对象
        System.out.println(s1.equals(s3)); // true s1 s3 使用String类重载的方法比较的是他们的内容
    }
}

public class ContainerTest {
    public static void main(String[] args) {
        System.out.println("Fin");
    }
}
