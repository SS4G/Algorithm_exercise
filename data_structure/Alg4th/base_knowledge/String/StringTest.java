package AlgorithmTraining.data_structure.Alg4th.base_knowledge.String;

/**
 * Created by G5501 on 2017/9/2.
 * f
 */
import java.util.*;
public class StringTest {
    public static void main(String[] args) {
        Map<String, String> testCases = new HashMap<>();
        testCases.put("ABCABCVER", "ABCV");
        testCases.put("123456", "123456");
        testCases.put("123456", "7890");
        testCases.put("ABCDABCDE", "ABCDEF");
        testCases.put("AABRAACADABRAACAADABRA", "AACAA");
        testCases.put("FINDINAHAYSTACKNEEDLE", "NEEDLE");
        StringFinder naive = new NaiveMatch();
        StringFinder kmp = new KMPMatch();
        //yyyStringFinder boyermoore = new BoyerMooreMatch();
        for (String s : testCases.keySet()) {
            assert naive.find(s, testCases.get(s)) == s.indexOf(testCases.get(s));
            assert kmp.find(s, testCases.get(s)) == s.indexOf(testCases.get(s));
            //assert boyermoore.find(s, testCases.get(s)) == s.indexOf(testCases.get(s));
        }
    }
}
