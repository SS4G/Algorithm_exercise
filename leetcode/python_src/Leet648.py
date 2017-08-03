class TrieNode:
    def __init__(self):
        self.isWord = False
        self.chars = {}

class Solution(object):
    def replaceWords(self, dict, sentence):
        """
        :type dict: List[str]
        :type sentence: str
        :rtype: str
        """
        trie = TrieNode()
        for word in dict:
            ptr = trie
            for c in word:
                if c not in ptr.chars:
                    ptr.chars[c] = TrieNode()
                ptr = ptr.chars[c]
            ptr.isWord = True
        # self.showTrie(trie, 0)
        words = sentence.split()
        newSentence = []
        for word in words:
            stack = []
            ptr = trie
            getRootFlag = False
            for c in word:
                if c in ptr.chars:
                    stack.append(c)
                    if ptr.chars[c].isWord is True:
                        # print("XX")
                        getRootFlag = True
                        break
                    ptr = ptr.chars[c]
                else:
                    break
            if not getRootFlag:
                newSentence.append(word)
            else:
                newSentence.append("".join(stack))
        return " ".join(newSentence)

    def showTrie(self, root, level):
        if root.isWord is True:
            print(level * " " + "flag")
        if len(root.chars) > 0:
            for k in root.chars.keys():
                print(level * " " + k)
                self.showTrie(root.chars[k], level=level + 1)


if __name__ == "__main__":
    s = Solution()
    dict0 = ["cat", "bat", "rat"]
    sentence = "the cattle was rattled by the battery"
    print(s.replaceWords(dict0, sentence))

