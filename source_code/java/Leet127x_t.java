package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/12.
 */
import java.util.*;
class Leet127x {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<Character> charSet = new HashSet<>();
        Map<String, Integer> wordMap = new HashMap<>();
        Map<String, List<String>> lastWord = new HashMap<>();
        for (String s : wordList) {
            wordMap.put(s, Integer.MAX_VALUE);
        }

        wordMap.put(beginWord, 0);
        wordMap.put(endWord, Integer.MAX_VALUE);

        for (String s : wordList) {
            char[] tmpArr = s.toCharArray();
            for (char c : tmpArr) {
                charSet.add(c);
            }
        }

        int wordLen = beginWord.length();
        String curWord = beginWord;
        List<String> sFifo = new ArrayList<>();
        sFifo.add(beginWord);
        int wr = 1;
        int rd = 0;
        while (rd < wr) {
            curWord = sFifo.get(rd);
            for (int i = 0; i < wordLen; i++) {
                char[] tmpArr = curWord.toCharArray();
                for (char c : charSet) {
                    tmpArr[i] = c;
                    String ss = new String(tmpArr);
                    if (wordMap.containsKey(ss) && wordMap.get(ss) == Integer.MAX_VALUE) {
                        //System.out.println(tmpfile);
                        wordMap.put(ss, wordMap.get(curWord) + 1);
                        /*
                        if (!lastWord.containsKey(tmpfile))
                            lastWord.put(tmpfile, new ArrayList<>());
                        lastWord.get(tmpfile).add(curWord);*/
                        sFifo.add(ss);
                        wr++;
                    }
                }
            }
            rd++;
        }

        if (wordMap.get(endWord) != Integer.MAX_VALUE) {
            return wordMap.get(endWord) + 1;
        }
        else {
            return 0;
        }
    }
}

public class Leet127x_t {
    public static void main(String[] args) {
        Leet127x leet = new Leet127x();
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        int x = leet.ladderLength(beginWord, endWord, Arrays.asList(wordList));
        System.out.println(x);
    }
}
