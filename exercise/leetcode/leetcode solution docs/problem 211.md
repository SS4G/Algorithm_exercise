## 211. Add and Search Word - Data structure design

Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:


```
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
```

Note:
You may assume that all words are consist of lowercase letters a-z.

#### tips
使用trie树 对于通配符的查找应该使用 递归 
trie树使用字典更加高效

#### mycode

```
class TrieNode(object):
    def __init__(self, val):
        self.wordFlag = False
        self.isRoot = False
        self.val = val
        self.chars = [None, ] * 26  # map to 26 characters


class WordDictionary(object):
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.root = TrieNode("st")

    def toInt(self, c):
        return ord(c) - ord('a')

    def addWord(self, word):
        """
        Adds a word into the data structure.
        :type word: str
        :rtype: void
        """
        curPtr = self.root
        for c in range(len(word)):
            if curPtr.chars[self.toInt(word[c])] is None:
                curPtr.chars[self.toInt(word[c])] = TrieNode(word[c])
                curPtr = curPtr.chars[self.toInt(word[c])]
            else:
                curPtr = curPtr.chars[self.toInt(word[c])]

            if c == len(word) - 1:
                curPtr.wordFlag = True

    def search(self, word):
        """
        Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
        :type word: str
        :rtype: bool
        """
        word = list(word)
        return self.dfsSearch(self.root, word, 0)

    def dfsSearch(self, curNode, word, index):
        while True:
            if index < len(word):
                c = word[index]
            else:
                return False
            if c != ".":
                cint = self.toInt(c)
                if curNode.chars[cint] is None:  # 找不到这个字符
                    return False
                elif index == len(word) - 1 and curNode.chars[cint].wordFlag is False:  # 找到了字符但是不是单词结尾
                    return False
                elif index == len(word) - 1 and curNode.chars[cint].wordFlag is True:
                    return True
                else:
                    index += 1
                    curNode = curNode.chars[cint]
            else:
                res = False
                for offset in range(26):
                    c0 = chr(offset + ord('a'))
                    word[index] = c0
                    res = res or self.dfsSearch(curNode, word, index)
                    if res:
                        return True
                return False
```

