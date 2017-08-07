package AlgorithmTraining.learning.java_learn;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Random;

/**
 * Created by 903 on 2017/8/6.
 */
public class ArrayTest {
    public static void main(String[] args) {
        Integer[] array = new Integer[20];
        //填充数组：
        Arrays.fill(array, 1);
        //将数组的第2和第3个元素赋值为8：
        Arrays.fill(array, 2, 4, 8);
        dispArray(array);
        Random random = new Random();
        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt(10);
        dispArray(array);
        Arrays.sort(array);
        dispArray(array);
        System.out.println(Arrays.binarySearch(array, 9));
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        System.out.println("a:" + Boolean.toString(a.hashCode() == b.hashCode())); //false
        // 只要有相同的内容, hashcode 就像同
        System.out.println("b:" + Boolean.toString(Arrays.hashCode(a) == Arrays.hashCode(b))); //true
        System.out.println(a.equals(b)); //false
        System.out.println(Arrays.equals(a, b)); // true
        Integer[] c = {1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] d;
        d = Arrays.copyOf(c, 4);
        dispArray(d); // 1， 2， 3， 4
        Integer[] arr0 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] arr1 = {-1, -2, -3, -4, -5, -6, -7, -8, -9, -10};
        System.arraycopy(arr0, 3, arr1, 5, 4);
        //类似于strncpy(); 指定原地址与目的地地址
        dispArray(arr1);


        /*
        Arrays.sort(array,2,7)：
        7 8 2 3 3 6 12 5 4
        对整个数组进行排序：Arrays.sort(array1)：
        2 3 3 4 5 6 7 8 12
        比较数组元素是否相等:Arrays.equals(array, array1):
        false
        克隆后数组元素是否相等:Arrays.equals(array1, array2):
        true
        元素3在array1中的位置：Arrays.binarySearch(array1, 3)：
        1
        元素9在array1中的位置：Arrays.binarySearch(array1, 9)：
        -9
            */
    }
    private static void dispArray(Object[] arr) {
        for (Object o: arr) {
            System.out.print(o.toString() + ",");
        }
        System.out.println("\n");
    }
}
