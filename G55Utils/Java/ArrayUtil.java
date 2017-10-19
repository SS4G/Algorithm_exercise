package AlgorithmTraining.G55Utils.Java;

/**
 * Created by BUPT_SS4G on 2017/10/13.
 */
public class ArrayUtil {
    public static void showArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            sb.append(',');
        }
        sb.append(']');
        System.out.println(sb);
    }

    public static void showArr(Integer[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            sb.append(',');
        }
        sb.append(']');
        System.out.println(sb);
    }

    public static void showArr(Object[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i].toString());
            sb.append(',');
        }
        sb.append(']');
        System.out.println(sb);
    }

    public static void showArr2D(int[][] arr) {
        for (int[] arr0 : arr) {
            showArr(arr0);
        }
    }
}
