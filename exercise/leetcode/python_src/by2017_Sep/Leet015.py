class Solution(object):
    """
        def find(self, arr, lo, hi, target):
        while lo <= hi:
            mid = (lo + hi) >> 1
            if arr[mid] == target:
                return mid
            elif arr[mid] < target:
                lo = mid + 1
            elif arr[mid] > target:
                hi = mid - 1
        return -1
    """
    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """

        nums.sort()
        lastNeg = -1
        firstPos = -1
        for i in range(len(nums)):
            if nums[i] < 0:
                if i+1 >= len(nums) or nums[i + 1] >= 0:
                    lastNeg = i
            if nums[i] > 0:
                if i == 0 or nums[i - 1] <= 0:
                    firstPos = i
        zeroAmounts = firstPos - lastNeg - 1
        res = []
        if firstPos == 0 or lastNeg == len(nums) - 1:
            return []
        elif firstPos == -1 and lastNeg == -1:
            return [[0, 0, 0]] if len(nums) >= 3 else []


        posSet = set(nums[firstPos:])
        negSet = set(nums[:lastNeg + 1])
        if zeroAmounts > 0:  # at least 1 zero
            for pos in posSet:
                if -pos in negSet:
                    res.append((pos, 0, -pos))
            if zeroAmounts >= 3:
                res.append((0, 0, 0))

        for i in range(firstPos, len(nums)):
            for j in range(i + 1, len(nums)):
                posPart = nums[i] + nums[j]
                if -posPart in negSet:
                    res.append((nums[i], nums[j], -posPart))

        for i in range(0, lastNeg + 1):
            for j in range(i + 1, lastNeg + 1):
                negPart = nums[i] + nums[j]
                if -negPart in posSet:
                    res.append((nums[i], nums[j], -negPart))

        return [[item[0], item[1], item[2]] for item in set(res)]

if __name__ == "__main__":
    s = Solution()
    nums = [-1, 0, 0, 0, 1, 2, 2, 2, -1, -4]
    nums = [-1, -1, -4]
    nums = [1, 1, 4]
    nums = [0, 0, 0, 0]
    nums = [0, ]
    nums = [1, 1, ]
    nums = [1, -1, ]
    print(s.threeSum(nums))


