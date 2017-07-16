class Solution(object):
    def optimalDivision(self, nums):
        """
        :type nums: List[int]
        :rtype: str
        """
        if len(nums) == 0:
            return ""
        nums = [float(i) for i in nums]
        res, str0 = self.findMostValue(nums, findMax=True)
        return str0

    def findMostValue(self, nums, findMax=True):
        if len(nums) == 1:
            str0 = ("x" + str(nums[0])).strip("0").strip(".") if "." in str(nums[0]) else "x" + str(nums[0])
            return nums[0], str0[1:]
        elif len(nums) == 2:
            str0 = ("x" + str(nums[0])).strip(".0").strip(".") if "." in str(nums[0]) else "x" + str(nums[0])
            str1 = ("x" + str(nums[1])).strip(".0").strip(".") if "." in str(nums[1]) else "x" + str(nums[1])
            return nums[0] / nums[1], str0[1:] + "/" + str1[1:]
        curMaxStr = ""
        curMinStr = ""
        curMax = -float("inf")
        curMin = float("inf")
        for i in range(len(nums) - 1):
            numrator = nums[:i + 1]
            deNumrator = nums[i + 1:]
            if findMax:
                num, numMark = self.findMostValue(numrator, findMax=True)
                deNum, deNumMark = self.findMostValue(deNumrator, findMax=False)
                tmpVal = num / deNum
                if tmpVal > curMax:
                    curMax = tmpVal
                    if "/" in deNumMark:
                        curMaxStr = numMark + "/" + "(" + deNumMark + ")"
                    else:
                        curMaxStr = numMark + "/" + deNumMark
            else:
                num, numMark = self.findMostValue(numrator, findMax=False)
                deNum, deNumMark = self.findMostValue(deNumrator, findMax=True)
                tmpVal = num / deNum
                if tmpVal < curMin:
                    curMin = tmpVal
                    if "/" in deNumMark:
                        curMinStr = numMark + "/" + "(" + deNumMark + ")"
                    else:
                        curMinStr = numMark + "/" + deNumMark
        if findMax:
            return curMax, curMaxStr
        else:
            return curMin, curMinStr

if __name__ == "__main__":
    s = Solution()
    nums = [10000, 1000, 100, 10, 200]
    #nums = [1000,100,10,2]
    val, str0 = s.findMostValue(nums)
    print(str0)