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
                self.dfsSearch(curNode, word, index + 1)
                return False

    def showTrie(self, root, layer):
        curPtr = root
        if curPtr is None:
            return
        print("---" * layer + str(curPtr.val) + ("" if not curPtr.wordFlag else " W"))
        for subTree in root.chars:
            self.showTrie(subTree, layer + 1)

        # Your WordDictionary object will be instantiated and called as such:
        # obj = WordDictionary()
        # obj.addWord(word)
        # param_2 = obj.search(word)

if __name__ == "__main__":
    w = WordDictionary()
    ops = ["addWord","addWord","addWord","addWord","addWord","addWord","addWord","addWord","search","search","search","search","search","search","search","search","search","search"]
    args = [["ran"],["rune"],["runner"],["runs"],["add"],["adds"],["adder"],["addee"],["r.n"],["ru.n.e"],["add"],["add."],["adde."],[".an."],["...s"],["....e."],["......."],["..n.r"]]

    for i in range(len(ops)):
        if ops[i] == "addWord":
            w.addWord(args[i][0])
            print("add", args[i][0])
        #else:
        #    print("search", args[i][0])
        #   print(w.search(args[i][0]))
    w.showTrie(w.root, 0)
    print(w.search("..nner"))

