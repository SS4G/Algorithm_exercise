## 30. Substring with Concatenation of All Words
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).

#### tips
这个方法使用一个固定长度的滑动窗口，只不过窗口内部的元素是单词而不是字符 用一个哈希表来记录当前窗口中 对于pattern的满足情况 这样的复杂度为O(n)

#### mycode
```Java
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
```
