package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/10/11.
 */

class Leet030x2 {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> freatureMap = genFreatureMap(s, words);

        int wordLen = words[0].length();
        int wordAmount = words.length;
        List<Integer> res = new ArrayList<>();
        for (int offset = 0; offset < wordLen; offset++) {
            res.addAll(matchwords(freatureMap, offset, s, wordLen, wordAmount * wordLen));
        }
        return res;
    }

    private Map<String, Integer> genFreatureMap(String s, String[] words) {
        Map<String, Integer> freatureMap = new HashMap<>();
        for (String word : words) {
            if (!freatureMap.containsKey(word))
                freatureMap.put(word, 0);
            freatureMap.put(word, freatureMap.get(word) + 1);
        }
        return freatureMap;
    }

    private List<Integer> matchwords(Map<String, Integer> freatureMap, int offset, String s, int wordLength, int windowLength) {
        List<Integer> res = new ArrayList<>();
        Map<String, Integer> tmpFeatureMap = new HashMap<>(freatureMap);
        int wordAmount = windowLength / wordLength;
        for (int i = 0; i < wordAmount; i++) { // fill first window
            int wordStIdx = offset + i * wordLength;
            if (wordStIdx + wordLength <= s.length()) {
                String existWord = s.substring(wordStIdx, wordStIdx + wordLength);
                if (tmpFeatureMap.containsKey(existWord)) {
                    tmpFeatureMap.put(existWord, tmpFeatureMap.get(existWord) - 1);
                }
            }
        }
        if (satisfy(tmpFeatureMap)) {
            res.add(offset);
        }
        int st = offset;
        while (st <= s.length() - windowLength) {
            //System.out.println("st = " + st);
            String delWord = s.substring(st, st + wordLength);
            if (tmpFeatureMap.containsKey(delWord)) {
                tmpFeatureMap.put(delWord, tmpFeatureMap.get(delWord) + 1);
            }
            st += wordLength;
            if (st + windowLength <= s.length()) {
                String addWord = s.substring(st + windowLength - wordLength, st + windowLength);
                if (tmpFeatureMap.containsKey(addWord)) {
                    tmpFeatureMap.put(addWord, tmpFeatureMap.get(addWord) - 1);
                }
            }
            if (satisfy(tmpFeatureMap)) {
                res.add(st);
            }
        }
        return res;
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
    Leet030x2 leet = new Leet030x2();
    String[] strs = {"foo", "bar"};
    String s = "barfoothefoobarmaniibarfooiisiibarfoo";
    //Map<String, Integer> fMap = leet.genFreatureMap(s, strs);
    //System.out.println(fMap);
    //System.out.println(leet.matchwords(fMap, 0, s, 3, 3 * 2));leet
    //List<Integer> x = leet.findSubstring(s, strs);
    //System.out.println(x);
    s = "a";
    strs = new String[]{"a", "a"};
    System.out.println(leet.findSubstring(s, strs));
    }
}
