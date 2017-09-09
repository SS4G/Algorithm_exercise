## 127. Word Ladder

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
For example,

Given:
beginWord = "hit"
endWord = "cog"

```
wordList = ["hot","dot","dog","lot","log","cog"]
```
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:

```
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
```

UPDATE (2017/1/20):
The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.

#### tips
这个题目实际上是一个 图 所谓的差一个字符的单词也叫做 编辑距离为1 也可以理解成汉明距是1
可以吧所有点汉明距为1的点对 看做是相连的 然后表示出一个图 然后使用BFS

但是对于这个题目由于只是要算一个路径 所以 选中一个点后 可以通过迭代器的方式去查找下一个距离为1的点 而不用一次吧所有的图都搞出来 一次搞一个图 会很费时间所以不容用到那些点再添加那些点即可 

#### mycode

```
class Solution(object):
    def ladderLength(self, beginWord, endWord, wordList):
        """
        :type beginWord: str
        :type endWord: str
        :type wordList: List[str]
        :rtype: int
        """
        wordSet = set(wordList)
        marked = {k: False for k in wordList}
        marked[beginWord] = True
        fifo = [(beginWord, 0), ]
        i = 0
        distance = 0
        found = False
        while i < len(fifo):
            adj = self.getNextAdjWord(fifo[i][0], wordSet)
            distance = fifo[i][1] + 1
            for word in adj:
                if not marked[word]:
                    fifo.append((word, distance))
                    marked[word] = True
                    if word == endWord:
                        found = True
            else:
                if found:
                    break
            i += 1
        return 0 if not found else distance + 1

    def getNextAdjWord(self, s0, wordSet):
        res = []
        for j in range(len(s0)):
            for i in "abcdefghijklmnopqrstuvwxyz":
                if i != s0[j]:
                    nextWord = s0[:j] + i + s0[j + 1:]
                    if nextWord in wordSet:
                        res.append(nextWord)
        return res
```
