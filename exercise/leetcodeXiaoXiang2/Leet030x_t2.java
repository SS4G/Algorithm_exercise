package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/10/11.
 */

class Leet030x2 {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> freatureMap = new HashMap<>();
        for (String word : words) {
            if (!freatureMap.containsKey(word))
                freatureMap.put(word, 0);
            freatureMap.put(word, freatureMap.get(word) + 1);
        }

        int wordLen = words[0].length();
        int wordAmount = words.length;
        for (int offset = 0; offset < wordLen; offset++) {

        }
    }

    private List<Integer> matchwords(Map<String, Integer> freatureMap, int offset, String s, int wordLength, int windowLength) {
        List<Integer> res = new ArrayList<>();
        Map<String, Integer> tmpFeatureMap = new HashMap<>(freatureMap);
        int st = offset;
        int wordAmount = windowLength / wordLength;
        for (int i = 0; i < wordAmount; i++) {
            int wordStIdx = st + i * wordLength;
            String existWord = s.substring(wordStIdx, wordStIdx + wordLength);
            if (tmpFeatureMap.containsKey(existWord)) {
                tmpFeatureMap.put(existWord, tmpFeatureMap.get(existWord) - 1);
                if (satisfy(tmpFeatureMap)) {
                    res.add()
                }
            }
        }
        while (st < s.length() - windowLength) {

        }
    }

    private boolean satisfy(Map<String, Integer> charCnt) {
        for (String c : charCnt.keySet()) {
            if (charCnt.get(c) != 0)
                return false;
        }
        return true;
    }
}

public class Leet030x_t2 {
    public static void main(String[] args) {
    Leet030x leet = new Leet030x();
    String[] strs = {"foo", "foo"};
    String s = "barfoothefoofooman";
    List<Integer> x = leet.findSubstring(s, strs);
    System.out.println(x);
}
}
