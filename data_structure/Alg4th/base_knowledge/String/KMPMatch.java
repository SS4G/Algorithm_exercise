package AlgorithmTraining.data_structure.Alg4th.base_knowledge.String;

/**
 * Created by mi on 17-4-1.
 * complete by g5501 on 17-9-2
 */
import java.util.*;

public class KMPMatch implements StringFinder {
    /**
     * 用于KMP匹配 sourec中的pattern
     * 复杂度O(m+n) m为 pattern长度 n为主串长度
     * @param source  被搜索的主串
     * @param pattern 要在主串中搜索的模式
     * @return 首次出现 pattern的起始下标 起始下标从0 开始
     *
     */
    private final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private HashMap<Character, Integer> charIdx = new HashMap<>();

    {
        for (int i = 0; i < ALPHABET.length(); i++) {
            charIdx.put(ALPHABET.charAt(i), i);
        }
    }

    public int find(String source, String pattern){
        char [] srcArr = source.toCharArray();
        char [] patArr = pattern.toCharArray();
        int sPtr = 0;
        int pPtr = 0;
        int[][] dfa = generateDFA(pattern);
        while (sPtr < source.length() && pPtr < pattern.length()) {
            pPtr = dfa[charIdx.get(source.charAt(sPtr))][pPtr];
            sPtr++;
        }
        if (pPtr >= pattern.length()) {
            return sPtr - pattern.length();
        }
        else
            return -1;
    }

    private int[][] generateDFA(String pattern) { //生成模式字符串特有的匹配数组
        int[][] dfa = new int[ALPHABET.length()][pattern.length()];
        for (Character c : ALPHABET.toCharArray()) {
            int charIndex = charIdx.get(c);
            dfa[charIndex][0] = 0;
        }
        dfa[charIdx.get(pattern.charAt(0))][0] = 1;

        for (int j = 1, X = 0; j < pattern.length(); j++) {
            for (Character c : ALPHABET.toCharArray()) {
                int charIndex = charIdx.get(c);
                dfa[charIndex][j] = dfa[charIndex][X];
            }
            dfa[charIdx.get(pattern.charAt(j))][j] = j + 1;
            X = dfa[charIdx.get(pattern.charAt(j))][X];//??
        }
        return dfa;
    }
}
