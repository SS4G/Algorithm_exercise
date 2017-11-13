## 126. Word Ladder II
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
For example,

Given:

```
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
```

Return

```
[
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
```

- Note:
- Return an empty list if there is no such transformation sequence.
- All words have the same length.
- All words contain only lowercase alphabetic characters.
- You may assume no duplicates in the word list.
- You may assume beginWord and endWord are non-empty and are not the same.
- 


#### tips
和127题一样 都是使用的是BFS 但是与127不同的是这个题目得使用一个字典来记录所有的最短路径。 然后对这个记录着路径的字典进行翻译

#### mycode

```JAVA
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
                    //System.out.println(ss);
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

    public List<List<String>> maps(Map<String, List<String>> lastWord, String endWord, String beginWord) {//字典翻译函数
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
```
