package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 *
 * Created by BUPT_SS4G on 2017/10/10.
 */
import java.util.*;
class Leet030x {
    public List<Integer> findSubstring(String s, String[] words) {
        HashMap<String, Integer> wordsDict = new HashMap<>();
        for (String s0 : words) {
            if (!wordsDict.containsKey(s0)) {
                wordsDict.put(s0, 0);
            }
            wordsDict.put(s0, wordsDict.get(s0) + 1);
        }

        int wordLen = words[0].length();
        HashMap<Character, Integer> freatureMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();


        for (String str : words) {//build feature dict
            for (Character c : str.toCharArray()) {
                if (!freatureMap.containsKey(c))
                    freatureMap.put(c, 0);
                freatureMap.put(c, freatureMap.get(c) + 1);
            }
        }
        //System.out.println("freatureMap:" + freatureMap);
        HashMap<Character, Integer> tmpMap = (HashMap<Character, Integer>)freatureMap.clone();
        for (int i = 0; i < words.length * wordLen; i++) { //first window
            if (i >= s.length()) {
                return new ArrayList<>();
            }
            else {
                char c = s.charAt(i);
                if (tmpMap.containsKey(c)) {
                    tmpMap.put(c, tmpMap.get(c) - 1);
                }
            }
        }

        //System.out.println("tmpMap:" + tmpMap);

        if (allZero(tmpMap) && allFoundInDict(s.substring(0, words.length * wordLen), wordsDict, wordLen)) {
            result.add(0);
        }
        //System.out.println(wordsDict);
        for (int i = words.length * wordLen; i < s.length(); i++) {
            char tobeRemove = s.charAt(i - words.length * wordLen);
            char tobeAdd = s.charAt(i);
            if (tmpMap.containsKey(tobeRemove))
                tmpMap.put(tobeRemove, tmpMap.get(tobeRemove) + 1);
            if (tmpMap.containsKey(tobeAdd))
                tmpMap.put(tobeAdd, tmpMap.get(tobeAdd) - 1);
            //System.out.println(i - words.length * wordLen + 1 + ":" + tmpMap);
            if (allZero(tmpMap) && allFoundInDict(s.substring(i - words.length * wordLen + 1, i + 1), wordsDict, wordLen)) {
                result.add(i - words.length * wordLen + 1);
            }
        }
        return result;
    }

    private boolean allZero(HashMap<Character, Integer> map0) {
        for (Character c : map0.keySet()) {
            if (map0.get(c) != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean allFoundInDict(String s0, HashMap<String, Integer> wordsDict, int wordLen) {
        HashMap<String, Integer> tmpSet = (HashMap<String, Integer>) wordsDict.clone();
        for (int i = 0; i < s0.length(); i += wordLen) {
            String tofindWord = s0.substring(i, i + wordLen);
            if (tmpSet.containsKey(tofindWord) && tmpSet.get(tofindWord) > 0) {
                tmpSet.put(tofindWord, tmpSet.get(tofindWord) - 1);
            }
            else {
                return false;
            }
        }
        return true;
    }
}

public class Leet030x_t {
    public static void main(String[] args) {
        Leet030x leet = new Leet030x();
        String[] strs = {"foo", "foo"};
        String s = "barfoothefoofooman";
        List<Integer> x = leet.findSubstring(s, strs);
        System.out.println(x);
    }
}
