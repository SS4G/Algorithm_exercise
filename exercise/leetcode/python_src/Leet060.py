class Solution(object):
    def getPermutation(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: str
        """
        fact = [0, ] * 10
        digits = [i for i in range(10)]
        marked = [False, ] * 10
        for i in range(10):
            if i == 0 or i == 1:
                fact[i] = 1
            else:
                fact[i] = fact[i - 1] * i
        i = 1
        totalOffset = 0
        res = []
        while i <= n:
            curFact = fact[n - i]
            factOffset = ((k - totalOffset - 1) // curFact)
            # print("i=", i, "curFact=", curFact, "factOffset=", factOffset)
            res.append(self.getOffset(digits, factOffset + 1, marked))
            totalOffset += factOffset * curFact
            i += 1
        return "".join([str(i) for i in res])

    def getOffset(self, arr, offsetPos, marked):
        i = 0
        j = 0
        while i <= offsetPos + 2:
            if not marked[j]:
                i += 1
                if i == offsetPos + 1:
                    marked[j] = True
                    #print("j=", j)
                    #print(marked)
                    return arr[j]
            j += 1

if __name__ == "__main__":
    s = Solution()
    print(s.getPermutation(9, 54494))
