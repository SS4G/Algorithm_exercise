package AlgorithmTraining.java_learn;

/**
 * Created by g55 on 2017/6/1.
 */
import java.util.*;
class ListTest {
    public static void main(String[] args) {
        String[] sArr = {"Abc", "Def", "Ghi", "Jkl"};
        List<String> sList = Arrays.asList(sArr); //会将sArr对象 赋值给一个不可变的 内部数组引用 所以sList的内部数组对象没有变
        for (int i = 0; i < sList.size(); i++)
            System.out.println("Element "+i+":"+sList.get(i)); //因为内部数组对象不可变 所以只能进行读取操作
        // sList.add("Mno") // 会引发 不支持操作的 异常因为Arrays.asList没有实现这个方法


        System.out.println("Show ArraysList sList0");
        List<String> sList0 = new ArrayList<String>(10);
        sList0.add("start");
        sList0.addAll(sList); // 类似于Python extend的 作用
        for (String s : sList0)
            System.out.println(s);

        System.out.println("Show LinkedList sList1");
        List<String> sList1 = new LinkedList<String>();
        sList1.add("start");
        sList1.addAll(sList); // 类似于Python extend的 作用
        for (String s : sList1)
            System.out.println(s);

        System.out.println();

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
