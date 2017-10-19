package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/12.
 */
import java.util.*;

class Leet126x {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
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
        int rd = 0;
        while (rd < sFifo.size()) {
            curWord = sFifo.get(rd);
            for (int i = 0; i < wordLen; i++) {
                char[] tmpArr = curWord.toCharArray();
                for (char c : charSet) {
                    tmpArr[i] = c;
                    String ss = new String(tmpArr);
                    //System.out.println(tmpfile);
                    int newDist = wordMap.get(curWord) + 1;
                    if (wordMap.containsKey(ss)) {
                        if (newDist < wordMap.get(ss)) {
                            wordMap.put(ss, newDist);
                            sFifo.add(ss);
                        }
                        if (newDist <= wordMap.get(ss)) {
                            if (!lastWord.containsKey(ss))
                                lastWord.put(ss, new ArrayList<>());
                            lastWord.get(ss).add(curWord);
                        }
                    }
                }
            }
            rd++;
        }
        //System.out.println(lastWord);
        if (!lastWord.containsKey(endWord))
            return new ArrayList<>();
        List<List<String>> paths = maps(lastWord, endWord, beginWord);
        //System.out.println(paths);
        return paths;
    }

    public List<List<String>> maps(Map<String, List<String>> lastWord, String endWord, String beginWord) {
        List<List<String>> paths = new ArrayList<>();
        List<String> start = new ArrayList<>();
        start.add(endWord);
        paths.add(start);
        List<List<String>> tmpPaths;
        String curWord = endWord;
        while (!curWord.equals(beginWord)) {
            tmpPaths = paths;
            paths = new ArrayList<>();
            for (List<String> onePath : tmpPaths) {
                List<String> lastPoint = lastWord.get(onePath.get(onePath.size() - 1));
                for (String lWord : lastPoint) {
                    List<String> newPath = new ArrayList<>(onePath);
                    newPath.add(lWord);
                    curWord = lWord;
                    paths.add(newPath);
                }
            }
        }

        for (List<String> onePath : paths) {
            Collections.reverse(onePath);
        }
        return paths;
    }

}

public class Leet126x_t {
    public static void main(String[] args) {
        Leet126x leet = new Leet126x();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"});
        leet.findLadders(beginWord, endWord, wordList);
    }
}
