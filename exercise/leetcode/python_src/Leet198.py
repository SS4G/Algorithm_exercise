class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        recDict = {}
        res = self.robRecursive(nums, len(nums) - 1, recDict)[0]
        return res

    def robRecursive(self, nums, finalIndex, recDict):
        if finalIndex in recDict:
            return recDict[finalIndex]
        if finalIndex == 0:
            recDict[finalIndex] = (nums[0], [0])
            return recDict[finalIndex]
        elif finalIndex == 1:
            if nums[0] < nums[1]:
                recDict[finalIndex] = (nums[1], [1])
                return recDict[finalIndex]
            elif nums[0] > nums[1]:
                recDict[finalIndex] = (nums[0], [0])
                return recDict[finalIndex]
            else:
                recDict[finalIndex] = (nums[1], [1, 0])
                return recDict[finalIndex]
        else:
            res0, lastIndex0 = self.robRecursive(nums, finalIndex - 1, recDict)
            res1, lastIndex1 = self.robRecursive(nums, finalIndex - 2, recDict)
            if len(lastIndex0) == 2:
                recDict[finalIndex] = (res0 + nums[finalIndex], [finalIndex,])
                return recDict[finalIndex]
            else:
                if lastIndex0[0] == finalIndex - 1:
                    tmpRes0, tmpIndex0 = res0, [finalIndex - 1, ]
                else:
                    tmpRes0, tmpIndex0 = res0 + nums[finalIndex], [finalIndex, ]

                tmpRes1, tmpIndex1 = res1 + nums[finalIndex], [finalIndex, ]
                if tmpRes1 > tmpRes0:
                    tmpRes0 = tmpRes1
                    tmpIndex0 = tmpIndex1
                elif tmpRes0 == tmpRes1:
                    if lastIndex0[0] == finalIndex - 1:
                        tmpIndex0.append(finalIndex - 1)

                recDict[finalIndex] = (tmpRes0,  tmpIndex0)
            return recDict[finalIndex]

if __name__ == "__main__":
    s = Solution()
    nums = [1, 3, 2, 7, 3, 9, 100, 3, 20, 400]
    #nums = [2, 2, 2, 7]
    print(s.rob(nums))