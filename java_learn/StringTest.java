package AlgorithmTraining.java_learn;

/**
 * Created by BUPT_SS4G on 2017/6/5.
 */
public class StringTest {
    public static void main(String[] args) {
        String s1 = "ABC"; // s1 s2 这些常量是放在 常量区的
        String s2 = "ABC";
        String s3 = new String("ABC"); // s3 使用new创建的是分配在堆区的
        System.out.println(s1 == s2); // true s1 s2 为常量被编译为 同一个常量对象
        System.out.println(s1 == s3); // false s1 s3 不是同一个对象
        System.out.println(s1.equals(s3)); // true s1 s3 使用String类重载的方法比较的是他们的内容
    }
}
