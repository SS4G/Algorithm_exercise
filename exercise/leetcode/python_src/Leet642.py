class TrieNode:
    def __init__(self):
        self.isEnd = False
        self.nextChars = {}
        self.hotDegree = 0

class AutocompleteSystem:
    def __init__(self, sentences, times):
        """
        :type sentences: List[str]
        :type times: List[int]
        """
        self.trieRoot = TrieNode()

    def input(self, c):
        """
        :type c: str
        :rtype: List[str]
        """

    def insertToTrie(self, word):
        triePtr = self.trieRoot
        for i in word:
            if i not in triePtr.nextChars:
                triePtr.nextChars[i] = TrieNode()
            triePtr = triePtr.nextChars[i]
        triePtr.isEnd = True
        triePtr.hotDegree = 0

    def getAllWords(self, prefix):
        triePtr = self.trieRoot
        for i in prefix:
            if i not in triePtr.nextChars:
                triePtr.nextChars[i] = TrieNode()
            triePtr = triePtr.nextChars[i]




        # Your AutocompleteSystem object will be instantiated and called as such:
        # obj = AutocompleteSystem(sentences, times)
        # param_1 = obj.input(c)