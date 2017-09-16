package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/12.
 */
import java.util.*;
class Solution044 {
    public String ReverseSentence(String str) {
        String[] words = str.split("\\s+");
        if (words.length == 0)
            return str;
        ArrayList<String> list = new ArrayList<>();
        for (String s : words) {
            list.add(s);
        }
        System.out.println(list);
        Collections.reverse(list);
        StringBuilder sb = new StringBuilder();
        for (int  i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }
}

public class No044 {
    public static void main(String[] args) {
        String str = "student. a am I";
        Solution044 s = new Solution044();
        System.out.println(s.ReverseSentence(str));
    }
}
