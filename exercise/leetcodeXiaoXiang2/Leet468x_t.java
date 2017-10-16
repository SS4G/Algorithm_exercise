package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/15.
 */
import java.util.*;
class Leet468x {
    Set<Character> charSet = new HashSet<>();;
    Leet468x() {
        for (int i = 0; i < 10; i++) {
            charSet.add((char)('0' + i));
        }
        charSet.add('a');
        charSet.add('b');
        charSet.add('c');
        charSet.add('d');
        charSet.add('e');
        charSet.add('f');
        charSet.add('A');
        charSet.add('B');
        charSet.add('C');
        charSet.add('D');
        charSet.add('E');
        charSet.add('F');
    }
    public String validIPAddress(String IP) {
        int cnt = 0;
        for (char c : IP.toCharArray())
            cnt += c == ':' ? 1 : 0;
        if (cnt != 7)
            return "Neither";

        if (IP.contains(":")) {
            String[] parts = IP.split(":");
            //System.out.println("ii");

            if (parts.length != 8)
                return "Neither";
            //System.out.println("ii");
            for (String s : parts) {
                if (s.length() == 0)
                    return "Neither";
                if (!validIPV6Number(s))
                    return "Neither";
            }
            return "IPv6";
        }
        else if (IP.contains(".")) {
            for (char c : IP.toCharArray())
                cnt += c == '.' ? 1 : 0;
            if (cnt != 3)
                return "Neither";

            //System.out.println("A");
            String[] parts = IP.split("\\.");
            //System.out.println(parts.length);
            if (parts.length != 4) {
                //System.out.println("ss");
                return "Neither";
            }
            for (String s : parts) {
                //System.out.println("B");
                if (s.length() == 0)
                    return "Neither";
                if (!validIPV4Number(s))
                    return "Neither";
            }
            return "IPv4";
        }
        else {
            return "Neither";
        }
    }

    boolean validIPV4Number(String str) {
        if (str.length() > 3)
            return false;
        for (char c : str.toCharArray())
            if (!Character.isDigit(c))
                return false;
        if (str.length() > 1 && str.charAt(0) == '0')
            return false;
        int val = Integer.parseInt(str);
        return val <= 255 && val >= 0;
    }

    boolean validIPV6Number(String str) {
        if (str.length() > 4 || str.length() <= 0)
            return false;
        for (char c : str.toCharArray()) {
            if (!charSet.contains(c))
                return false;
        }
        return true;
    }
}

public class Leet468x_t {
    public static void main(String[] args) {
        Leet468x leet = new Leet468x();
        //assert leet.validIPAddress("172.16.254.1").equals("IPv4");
        //assert leet.validIPAddress("172.16.254.01").equals("Neither");
        //assert leet.validIPAddress("172.16.257.1").equals("Neither");
        //System.out.println("2001:0db8:85a3:0:0:8A2E:0370:7334:".split(":").length);
        //assert leet.validIPAddress("2001:0db8:85a3:0000:0000:8a2e:0370:7334").equals("IPv6");
        assert leet.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:").equals("Neither");
        //assert leet.validIPAddress("2001:db8:85a3:0:0:8A2E:0370:7334").equals("IPv6");
        //assert leet.validIPAddress("2001:0db8:85a3::8A2E:0370:7334").equals("Neither");
        //assert leet.validIPAddress("02001:0db8:85a3:0000:0000:8a2e:0370:7334").equals("Neither");
    }
}
