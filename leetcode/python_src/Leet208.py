class TrieNode(object):
    def __init__(self, val):
        self.wordFlag = False
        self.isRoot = False
        self.val = val
        self.chars = {}  # map to 26 characters


class Trie(object):
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.root = TrieNode("st")

    def toInt(self, c):
        return ord(c) - ord('a')

    def insert(self, word):
        """
        Adds a word into the data structure.
        :type word: str
        :rtype: void
        """
        curPtr = self.root
        for c in range(len(word)):
            if word[c] not in curPtr.chars:
                curPtr.chars[word[c]] = TrieNode(word[c])
                curPtr = curPtr.chars[word[c]]
            else:
                curPtr = curPtr.chars[word[c]]

            if c == len(word) - 1:
                curPtr.wordFlag = True

    def search(self, word):
        """
        Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
        :type word: str
        :rtype: bool
        """
        return self.dfsSearch(self.root, word, 0, False)

    def dfsSearch(self, curNode, word, index, startWith):
        while True:
            if index < len(word):
                c = word[index]
            else:
                return False
            cint = c
            if cint not in curNode.chars:  # 找不到这个字符
                return False
            elif index == len(word) - 1 and curNode.chars[cint].wordFlag is False:  # 找到了字符但是不是单词结尾
                return False if not startWith else True
            elif index == len(word) - 1 and curNode.chars[cint].wordFlag is True:
                return True
            else:
                index += 1
                curNode = curNode.chars[cint]

    def startsWith(self, prefix):
        """
        Returns if there is any word in the trie that starts with the given prefix.
        :type prefix: str
        :rtype: bool
        """
        return self.dfsSearch(self.root, prefix, 0, True)


        # Your Trie object will be instantiated and called as such:
        # obj = Trie()
        # obj.insert(word)
        # param_2 = obj.search(word)
        # param_3 = obj.startsWith(prefix)