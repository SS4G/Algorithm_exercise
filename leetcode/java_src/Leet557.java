package AlgorithmTraining.leetcode.java_src;

/**
 * Created by mi on 17-4-10.
 * $todo debug
 */
class Solution {
    public String reverseWords(String s) {
            char[] sArr = s.toCharArray();
            int wordLo = 0;
            int wordHi = 0;
            int index = 0;
            int state;
            final int FOUND_START = 0;
            final int FOUND_CHAR = 1;
            final int FOUND_END = 2;

            state = FOUND_START;

            MAIN_LOOP:
            while (index < sArr.length) {
                switch (state) {
                    case FOUND_START:
                        if (!isWhiteSpace(sArr[index])) {
                            state = FOUND_CHAR;
                            wordLo = index;
                        }
                        else
                            state = FOUND_START;
                        index ++;
                        break;
                    case FOUND_CHAR:
                        if (!isWhiteSpace(sArr[index]) && index < sArr.length - 1)
                            state = FOUND_CHAR;
                        else if (!isWhiteSpace(sArr[index]) && index == sArr.length - 1) { //break loop directily
                            wordHi = index + 1;
                            System.out.println("lo = "+wordLo+" hi = "+wordHi);
                            reverseNonSpaceWords(sArr, wordLo, wordHi);
                            System.out.println(sArr);
                            break MAIN_LOOP;
                        }
                        else {
                            state = FOUND_START;
                            wordHi = index;
                            System.out.println("lo = "+wordLo+" hi = "+wordHi);
                            reverseNonSpaceWords(sArr, wordLo, wordHi);
                            System.out.println(sArr);
                        }
                        index ++;
                        break;
                 }
            }
            for (char i : sArr) {
                System.out.println(i);
            }
            return sArr.toString();
        }

        public void reverseNonSpaceWords(char[] arr, int lo, int hi) {
            // hi exclude
            char tmp;
            int halfSize = (hi - lo) >>> 1;
            for(int p = lo; p < hi; p++)
                System.out.print(arr[p]);
            System.out.println("-");
            for(int k = lo; k < lo + halfSize; k++) {
                tmp = arr[k];
                arr[k] = arr[hi - 1 - k];
                arr[hi - 1 - k] = tmp;
            }
            for(int p = lo; p < hi; p++)
                System.out.print(arr[p]);
            System.out.println("-");
        }

        private boolean isWhiteSpace(char a) {
            return a == ' ';
        }
    }
public class Leet557 {

    public static void main(String[] args) {
        Solution s = new Solution();
        char[] ar = "take abcd eeee".toCharArray();
        s.reverseNonSpaceWords(ar,5,9);

        /*String[] testcases = {"Let's take LeetCode contest"};//, "hello world!", "Hi Amy ", " Stop it! "};
        for (String testcase : testcases) {
            System.out.println(s.reverseWords(testcase));
        }*/
    }
}
