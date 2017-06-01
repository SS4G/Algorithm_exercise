package AlgorithmTraining.java_learn;

/**
 * Created by g55 on 2017/6/1.
 */
import java.util.*;
class ListTest {
    public static void main(String[] args) {

        //System.out.println(s1 == s2);
        //String[] x =
        //List<String> list0 = <String>
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
        Iterator<String> mIt = new MyIterator("  song.zi.heng.com.cn ").iterator();
        while (mIt.hasNext()) {
            System.out.println(mIt.next());
        }
        for(String s : mIt) {
            System.out.println(s);
        }
    }

    /*for(String s : mIt) {
        System.out.println();
    }*/
}

class MyIterator implements Iterable<String> {
    String[] it;
    public MyIterator(String str) {
        it = str.trim().split("\\.");
    }
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            int index = 0;
            @Override
            public String next() {
                index++;
                return it[index-1];
            }
            public boolean hasNext() {
                return index < it.length;
            }
        };
    }
}

class StringTest {
    public static void main(String[] args) {
        String s1 = "ABC"; // s1 s2 is at static area they are same object
        String s2 = "ABC";
        String s3 = new String("ABC"); // s3 is at heap
        System.out.println(s1 == s2); // true
        System.out.println(s1 == s3); // false
        System.out.println(s1.equals(s3)); // true
    }
}

public class ContainerTest {
    public static void main(String[] args) {
        System.out.println("Fin");
    }
}
