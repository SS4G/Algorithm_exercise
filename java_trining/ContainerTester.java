package AlgorithmTraining.java_trining;

/**
 * Created by BUPT_SS4G on 2017/5/24.
 */
import java.util.*;
public class ContainerTester {
    public static void main(String[] args) {
        Integer[] srcData = {1, 2, 3, 7, 2, 3, 1,};
        List<Integer> al0 = Arrays.asList(srcData); //这个返回的al0对象并没有真正的实现List 接口的add remove
        // 等修改数组长度的方法 相当于只是在 原始数组 srcdata的基础上增肌了一些制度的功能罢了
        List<Integer> al1 = new ArrayList<Integer>(Arrays.asList(srcData)); //这个才是真正可以实现的方法
        Collections.sort(al1); //对元素排序
        System.out.println("sorted al1 = " + al1); //直接使用 容器的tostring 方法可以将它们内部的内容打印出来
        Collections.shuffle(al1); //打乱顺序
        System.out.println("shuffled al1 = " + al1);
    }
}
