class Solution(object):
    def summaryRanges(self, nums):
        """
        :type nums: List[int]
        :rtype: List[str]
        """
        START_FOUND = 0
        END_FOUND = 1
        state = END_FOUND
        res = []
        rangePtr = -1
        i = 0
        while i < len(nums):
            if state == END_FOUND:
                st = nums[i]
                rangePtr = nums[i]
                i += 1
                state = START_FOUND
            else:
                if nums[i] == rangePtr + 1:
                    rangePtr = nums[i]
                    i += 1
                else:
                    res.append((st, rangePtr))
                    state = END_FOUND
        if state != END_FOUND:
            res.append((st, rangePtr))
        finalRes = []
        for r in res:
            if r[0] == r[1]:
                finalRes.append(str(r[0]))
            else:
                finalRes.append(str(r[0])+"->"+str(r[1]))
        return finalRes

if __name__ == "__main__":
    s = Solution()
    nums = [1,2,3,4,7,9,10,11,12,13,14,67]
    nums = [1,12]
    nums = []
    r = s.summaryRanges(nums)
    print(r)


