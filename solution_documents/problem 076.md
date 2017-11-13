## 76. Minimum Window Substring
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,

```
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".
```


Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

#### tips
使用一个字典来记录一个晃动窗口中当前的状态 还是老的方法 不满足条件是向右扩大窗口直到满足包含所有字符的条件，否则移动左标尺从左向右缩小窗口直到不满足条件
可以证明中间过程中必然会经历最小窗口 所以可以记录之

#### mycode

```Java
class Solution {    
    public String minWindow(String s, String t) {
        Map<Character, Integer> charCnt = new HashMap<>();
        for (char c : t.toCharArray()) {//build dict
            if (!charCnt.containsKey(c))
                charCnt.put(c, 0);
            charCnt.put(c, charCnt.get(c) + 1);
        }
        String minWindow = "";
        int minWindowLen = Integer.MAX_VALUE;
        int bg = 1;
        int ed = 0;

        //ed <= bg
        char c = s.charAt(0);
        if (charCnt.containsKey(c)) {
            charCnt.put(c, charCnt.get(c) - 1);
        }

        while (ed < s.length()) {
            if (satisfy(charCnt)) {
                //System.out.println(ed + ":" + bg);
                if (bg - ed < minWindowLen) {
                    minWindow = s.substring(ed, bg);
                    minWindowLen = minWindow.length();
                }

                c = s.charAt(ed);
                if (charCnt.containsKey(c)) {
                    charCnt.put(c, charCnt.get(c) + 1); //ed inc
                }
                ed++;
            }
            else {
                if (bg >= s.length())
                    break;
                c = s.charAt(bg);
                if (charCnt.containsKey(c)) {
                    charCnt.put(c, charCnt.get(c) - 1);
                }
                bg++;
            }
        }
        return minWindow;
    }

    private boolean satisfy(Map<Character, Integer> charCnt) {
        for (Character c : charCnt.keySet()) {
            if (charCnt.get(c) > 0)
                return false;
        }
        return true;
    }
}
```
