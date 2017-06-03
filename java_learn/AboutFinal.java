package AlgorithmTraining.java_learn;

/**
 * Created by g55 on 2017/6/2.
 */
public class AboutFinal {
    static int[] a = {1, 2, 3, 4, 5};
    public static void finalArgument(final int[] a) {
        for (int i : a)
            System.out.println(i);

        //a = new int[10]; //不可以改变final引用指向的对象
        a[0] = 10;
        //但是改变final引用对象中的其他引用是可以的
        //所以final这个只能保证自己直接做指向的对象不被改变
        //而不能保证对象内部的其他引用
        System.out.println("after modify the content of array a");
        for (int i : a)
            System.out.println(i);
    }
    public static void main(String[] args) {
        finalArgument(a);
    }

}
