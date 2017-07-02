class Solution(object):
    def findMaxForm(self, strs, m, n):
        """
        :type strs: List[str]
        :type m: int
        :type n: int
        :rtype: int
        """
        arr = [None, ] * len(strs)
        for i in range(len(strs)):
            arr[i] = (strs[i].count("0"), strs[i].count("1"))
        dpRec = [[0, ] * (n + 1) for i in range(m + 1)]
        for s in arr:
            i = m
            while i >= s[0]:
                j = n
                while j >= s[1]:
                    dpRec[i][j] = max(dpRec[i][j], dpRec[i - s[0]][j - s[1]] + 1)
                    j -= 1
                i -= 1
        return dpRec[m][n]

if __name__ == "__main__":
    s = Solution()
    strs = ["10", "0001", "111001", "1", "0"]
    a = s.findMaxForm(strs, 5, 3)
    print(a)
