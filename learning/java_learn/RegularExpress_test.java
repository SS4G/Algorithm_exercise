package AlgorithmTraining.learning.java_learn;

/**
 * Created by BUPT_SS4G on 2017/6/14.
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RegularExpress_test {
    public static void main(String[] args) {
        String regex0 = "(\\d{3}).*?(\\d{2})";
        String str0 = "123 456 789";
        String str1 = "x123 456 789";
        Pattern pattern = Pattern.compile(regex0);
        Matcher m0 = pattern.matcher(str0);
        Matcher m1 = pattern.matcher(str1);
        //System.out.println(""+m0.lookingAt());
        System.out.println(""+m1.lookingAt()); // 开头找不到
        System.out.println(""+m1.find()); //中间可以找到

        //System.out.println(m0.group(0));
        //System.out.println(m0.group(1));
        //System.out.println(m0.group(2));

        System.out.println(m1.group(0));
        System.out.println(m1.group(1));
        System.out.println(m1.group(2));

    }
}
