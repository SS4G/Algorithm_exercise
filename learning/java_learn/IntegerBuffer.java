package AlgorithmTraining.learning.java_learn;

import java.awt.*;

/**
 * Created by BUPT_SS4G on 2017/6/5.
 */
public class IntegerBuffer {
    public static void main(String[] args) {
        // 小整数缓存机制是为了更加高效的实现 因为小的整数 -128~127 使用的最为频繁
        // 其实是编译器的把戏
        int a = 10;
        int b = 10;
        Integer aI = a; //自动装箱
        Integer bI = b;
        int c = 10002;
        int d = 10002;
        Integer cI = c;
        Integer dI = d;
        System.out.println("aI==bI:"+(aI == bI)); //true
        //传说中的小整数缓存
        System.out.println("cI==dI:"+(cI == dI)); // false
        //大于128的整数不会被缓存
        System.out.println("aI==bI:"+(aI.equals(bI))); //true
        //调用equals方法 比较的是值
        System.out.println("cI==dI:"+(cI.equals(dI))); // true
        //调用equals方法 比较的是值
    }
}
