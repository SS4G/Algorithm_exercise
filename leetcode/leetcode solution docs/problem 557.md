## 557. Reverse Words in a String III Add to List

Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:

```
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
```
#### tips
使用了一个状态机来完成

#### mycode

```Java
class Solution {
    public String reverseWords(String s) {
            char[] sArr = s.toCharArray();
            int wordLo = 0;
            int wordHi = 0;
            int index = 0;
            int state;
            final int FOUND_START = 0;
            final int FOUND_CHAR = 1;

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
                            reverseNonSpaceWords(sArr, wordLo, wordHi);
                            break MAIN_LOOP;
                        }
                        else {
                            state = FOUND_START;
                            wordHi = index;
                            reverseNonSpaceWords(sArr, wordLo, wordHi);
                        }
                        index ++;
                        break;
                 }
            }
            return new String(sArr);
        }

        public void reverseNonSpaceWords(char[] arr, int lo, int hi) {
            // hi exclude
            char tmp;
            int halfSize = (hi - lo) >>> 1;
            for(int k = lo; k < lo + halfSize; k++) {
                tmp = arr[k];
                arr[k] = arr[hi - 1 - k + lo];
                arr[hi - 1 - k + lo] = tmp;
            }
        }

        private boolean isWhiteSpace(char a) {
            return a == ' ';
        }
    }
```
