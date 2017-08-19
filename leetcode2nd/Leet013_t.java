package AlgorithmTraining.leetcode2nd;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by 903 on 2017/7/21.
 *
 */
class Leet013 {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int[] mapInt = new int[s.length()];
        for (int i = 0; i < s.length(); i ++)
            mapInt[i] = map.get(s.charAt(i));
        int sum = 0;
        int i = 0;
        HashSet<Integer> leftSet = new HashSet<>();
        leftSet.add(1);
        leftSet.add(10);
        leftSet.add(100);
        leftSet.add(1000);
        while (i < s.length()) {
            if (i < s.length() - 1 && mapInt[i] < mapInt[i + 1] && leftSet.contains(mapInt[i])) {
                sum += (mapInt[i + 1] - mapInt[i]);
                i += 2;
            }
            else {
                sum += mapInt[i];
                i ++;
            }
        }
        return sum;
    }
}

public class Leet013_t {
    public static void main(String[] args) {
        Leet013 leet = new Leet013();
        assert leet.romanToInt("XCVIII") == 98: "WA";
        assert leet.romanToInt("DC") == 600: "WA";
       // assert leet.romanToInt("DC") == 200: "WA";
    }
}


