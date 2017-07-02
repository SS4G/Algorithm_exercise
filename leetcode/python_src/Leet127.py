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

if __name__ == "__main__":
    s = Solution()
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot", "dot", "dog", "lot", "log", "cog"]
    print(s.ladderLength(beginWord, endWord, wordList))

