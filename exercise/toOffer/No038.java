package AlgorithmTraining.exercise.toOffer;

import java.util.HashMap;

/**
 * Created by szh-920 on 17-9-11.
 */
class Solution038 {
    public int FirstNotRepeatingChar(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            }
            else {
                map.put(c, map.get(c) + 1);
            }
        }
        char[] arr = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (map.get(arr[i]) == 1)
                return i;
        }
        return 0;
    }
}

public class No038 {
    public static void main(String[] args) {
        String str = "abcdeabce";
        Solution038 s = new Solution038();
        System.out.println(s.FirstNotRepeatingChar(str));
    }
}
