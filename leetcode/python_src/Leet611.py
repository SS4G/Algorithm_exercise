class Solution:
    def triangleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        nums.sort()
        numDict = {}
        for i in range(len(nums)):
            if nums[i] not in numDict:
                numDict[nums[i]] = [i, 1]
            else:
                numDict[nums[i]][1] += 1
        cnt = 0
        for i in range(len(nums)):
            for j in range(i+1, len(nums)):
                if nums[i] == 0 or nums[j] == 0:
                    continue
                greaterIndex = self.findFirstGreaterEqualThan(nums, nums[i] + nums[j], j, len(nums) - 1)
                if greaterIndex >= len(nums):
                    greaterIndex = len(nums)
                elif nums[greaterIndex] in numDict:
                    greaterIndex = numDict[nums[greaterIndex]][0]
                cnt += greaterIndex - 1 - j
        return cnt

        def findFirstGreaterEqualThan(self, arr, target, lo, hi):
            while lo <= hi:
                mid = (lo + hi) >> 1
                if arr[mid] < target:
                    lo = mid + 1
                elif arr[mid] > target:
                    hi = mid - 1
                else:
                    return mid
            return lo
        """

        sides = list(numDict.keys())
        sides.sort()
        cnt = 0
        for i in range(len(sides)):
            for j in range(i+1, len(sides)):
                greaterIndex = self.findFirstGreaterEqualThan(sides, sides[i]+sides[j], j+1, len(sides) - 1)
                if greaterIndex

                for k in range(j+1, len(sides)):
                    if self.canBeTriangle(sides[i], sides[j], sides[k]):
                        cnt += numDict[sides[i]]*numDict[sides[j]]*numDict[sides[k]]
        for i in sides:
            if numDict[i] >= 3:
                n = numDict[i]
                cnt += (n*(n-1)*(n-2))//6
        for i in sides:
            if numDict[i] >= 2:
                n = numDict[i]
                side2 = n*(n-1)//2
                print(i, side2)
                for p in sides:
                    if p != i and p < 2*i:
                        cnt += side2*numDict[p]
        """

        return cnt

    def canBeTriangle(self, a, b, c):
        assert a <= b <= c, "invalid args"
        return (a + b) > c

    def tester(self, nums):
        sides = nums
        sides.sort()
        cnt = 0
        for i in range(len(sides)):
            for j in range(i+1, len(sides)):
                for k in range(j+1, len(sides)):
                    if self.canBeTriangle(sides[i], sides[j], sides[k]):
                        cnt += 1
        return cnt



if __name__ == "__main__":
    s = Solution()
    nums = [1, 1, 1, 1, 1]
    nums.sort()
    print(nums)
    # nums = [2, 2, 2, 2]

    # nums = [54, 9, 98, 5, 61, 83]
    print(s.triangleNumber(nums))
    print(s.tester(nums))