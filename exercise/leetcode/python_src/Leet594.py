class Solution(object):
    def findLHS(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        numDict = {}
        for i in nums:
            if i not in numDict:
                numDict[i] = 1
            else:
                numDict[i] += 1

        keys = list(numDict.keys())
        keys.sort()
        maxL = 0
        for i in range(len(keys)-1):
            if keys[i+1] - keys[i] == 1:
                nowL = numDict[keys[i]] + numDict[keys[i+1]]
                if nowL > maxL:
                    maxL = nowL
        return maxL

if __name__ == "__main__":
    s = Solution()
    print(s.findLHS([1,3,2,2,5,2,3,7]))