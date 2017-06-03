class Solution(object):
    def subsetsWithDup(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        nums.sort()
        res = self.subsetDupStep(nums, 0, len(nums))
        return res

    def subsetDupStep(self, nums, startIndex, N):
        tmpres = []
        if startIndex == N-1:
            return [[nums[startIndex], ], []]
        else:
            lastRes = self.subsetDupStep(nums, startIndex+1, N)
            # print("last:", lastRes, "index", startIndex, "nums[start]", nums[startIndex], nums[startIndex+1])
            if nums[startIndex] == nums[startIndex + 1]:
                for li in lastRes:
                     if len(li) > 0 and li[0] == nums[startIndex]:
                        tmpres.append([nums[startIndex], ] + li)
            else:
                for li in lastRes:
                    tmpres.append([nums[startIndex], ] + li)
            # print("first added", tmpres)
            tmpres.extend(lastRes)
        return tmpres


if __name__ == "__main__":
    s = Solution()
    x = s.subsetsWithDup([1, 2, 2, 3])
    assert False, "has some bug"
    for l in x:
        print(l)