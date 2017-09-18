class Solution(object):
    def fourSumCount(self, A, B, C, D):
        """
        :type A: List[int]
        :type B: List[int]
        :type C: List[int]
        :type D: List[int]
        :rtype: int
        """
        n = len(A)
        if n == 0:
            return 0
        abDict = {}
        cdDict = {}
        for i in range(n):
            for j in range(n):
                ab = A[i] + B[j]
                if ab not in abDict:
                    abDict[ab] = 1
                else:
                    abDict[ab] += 1
        for i in range(n):
            for j in range(n):
                cd = C[i] + D[j]
                if cd not in cdDict:
                    cdDict[cd] = 1
                else:
                    cdDict[cd] += 1
        cnt = 0
        for ab in abDict:
            if -ab in cdDict:
                cnt += abDict[ab]*cdDict[-ab]
        return cnt

if __name__ == "__main__":
    s = Solution()
    A = [1, 2]
    B = [-2, -1]
    C = [-1, 2]
    D = [0, 2]
    print(s.fourSumCount(A, B, C, D))
