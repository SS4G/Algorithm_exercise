## 212. Word Search II
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =


```
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
```

Return ["eat","oath"].  
Note:
You may assume that all inputs are consist of lowercase letters a-z.



#### tips
虽然也是回溯 但是候选列表可能会很大 所以 回溯的过程中总要去查找当前回溯的部分是否是 列表中某些词的前缀 如果是的话就应该 继续 如果不是就及早退回剪枝 这样就肯以达到很好地效率 为了能够实现前缀查找 应该使用trie树来完成这个任务


#### mycode
```Java
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        if (words.length == 0 || board.length == 0 || board[0].length == 0)
            return new ArrayList<>();
        Trie tr = new Trie();
        int maxLen = Integer.MIN_VALUE;
        for (String word : words) {
            tr.insert(word);
            maxLen = Math.max(maxLen, word.length());
        }

        boolean[][] marked = new boolean[board.length][board[0].length];
        for (boolean[] ba : marked)
            Arrays.fill(ba, false);
        Set<String> output = new HashSet<>();
        char[] buffer = new char[maxLen];
        for (int x = 0; x < board[0].length; x++) {
            for (int y = 0; y < board.length; y++) {
                dfsHelper(board, x, y, marked, buffer, 0, maxLen, output, tr);
            }
        }
        return new ArrayList<>(output);
    }

    public void dfsHelper(char[][] board, int x, int y, boolean[][] marked,
                          char[] curWord, int curIdx, int maxLen, Set<String> output, Trie tr) {
        if (curIdx < maxLen) {
            if (!marked[y][x]) {
                marked[y][x] = true;
                curWord[curIdx] = board[y][x];
                curIdx++;
                if (tr.startsWith(curWord, curIdx)) {
                    if (tr.search(curWord, curIdx)) {
                        output.add(new String(curWord, 0, curIdx));
                    }
                    List<List<Integer>> adjNodes = getAdj(board[0].length, board.length, x, y);
                    for (List<Integer> adjNode : adjNodes) {
                        int nextX = adjNode.get(0);
                        int nextY = adjNode.get(1);
                        dfsHelper(board, nextX, nextY, marked, curWord, curIdx, maxLen, output, tr);
                    }
                }
                marked[y][x] = false;
            }
        }
    }

    private List<List<Integer>> getAdj(int xL, int yL, int x, int y) {
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();

        if (x > 0)
            xs.add(x - 1);
        if (y > 0)
            ys.add(y - 1);
        if (x < xL - 1)
            xs.add(x + 1);
        if (y < yL - 1)
            ys.add(y + 1);
        List<List<Integer>> adjs = new ArrayList<>();
        for (int x0 : xs) {
            List<Integer> xx = new ArrayList<>();
            xx.add(x0);
            xx.add(y);
            adjs.add(xx);
        }

        for (int y0 : ys) {
            List<Integer> yy = new ArrayList<>();
            yy.add(x);
            yy.add(y0);
            adjs.add(yy);
        }
        return adjs;
    }
}


class TrieNode {
    HashMap<Character, TrieNode> map;
    boolean isWord;
    TrieNode() {
        isWord = false;
        map = new HashMap<>();
    }
}

class Trie {
    public TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode ptr = root;
        for (char c : word.toCharArray()) {
            if (!ptr.map.containsKey(c)) {
                ptr.map.put(c, new TrieNode());
            }
            ptr = ptr.map.get(c);
        }
        ptr.isWord = true;
    }

    public boolean search(char[] word, int ed) {
        TrieNode ptr = root;
        for (int i = 0; i < ed; i++) {
            char c = word[i];
            if (!ptr.map.containsKey(c)) {
                return false;
            }
            ptr = ptr.map.get(c);
        }
        return ptr.isWord;
    }

    public boolean startsWith(char[] prefix, int ed) {
        TrieNode ptr = root;
        for (int i = 0; i < ed; i++) {
            char c = prefix[i];
            if (!ptr.map.containsKey(c)) {
                return false;
            }
            ptr = ptr.map.get(c);
        }
        return true;
    }
}
```

