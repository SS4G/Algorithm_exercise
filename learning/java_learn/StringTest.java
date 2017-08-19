package AlgorithmTraining.learning.java_learn;

/**
 * Created by BUPT_SS4G on 2017/6/5.
 */
import java.io.*;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {
    final static int i = 0;
    public static void main(String[] args) {
        System.out.println(i);
        String s = "abcdeFGHIjklmn";
        System.out.println(s.toLowerCase());
        System.out.println(s.toUpperCase());
        StringBuilder sb = new StringBuilder();

        //String Builder 一个可以执行替换等操作的字符串对象相比String更高效 的 但是可以由StringBuilder转变为String
        sb.append("a");
        sb.append("b");
        sb.append(100);
        System.out.println(sb.toString());
        s = "ABC123FGH";
        sb = new StringBuilder(s);
        sb.replace(3, 6, "SDS");
        System.out.println(sb); //replace
        sb.delete(3, 6);
        System.out.println(sb);
        System.out.println(sb.length());
        System.out.println(s.length());
        System.out.println(s.contains("FGH"));
        String k = String.valueOf(10001);
        System.out.println(k.length());
        Integer z = new Integer(10001);
        System.out.println(z.toString());
        System.out.println(k.substring(0,3));
        Formatter f = new Formatter(System.out);
        f.format("%s, %05d\n", "AKQ", 100);
        String p = String.format("%s, %05d", "AKQ", 100);
        System.out.println(p);
        //System.out.println(f.toString());
        System.out.println("4".matches("[12345]"));

        String s0 = "123###ABC456";
        Pattern patt = Pattern.compile("(\\d+)(.+)"); //每个括号里面表示匹配的一部分
        Matcher m = patt.matcher(s0);
        if (m.find()) { //find 方法表示是否匹配到了一些对象
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.group(2));
        }

    }
}
