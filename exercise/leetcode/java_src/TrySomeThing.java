package AlgorithmTraining.exercise.leetcode.java_src;

import java.util.ArrayList;

/**
 * Created by BUPT_SS4G on 2017/5/11.
 */
public class TrySomeThing {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<String>(32);
        for (int i = 0 ; i< 35 ;i++) {
            arr.add("element:"+i);
        }

        arr.add(5, "EEEEEEEEEEEEE");

        //arr.remove(5);
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
