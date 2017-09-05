class Solution(object):
    def searchRange(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        targetIndex = self.findTarget(nums, target)
        if targetIndex == -1:
            return [-1, -1]
        # print("tar index =", targetIndex)
        leftLim = self.findPart(nums, target, True, targetIndex)
        print("S")
        rightLim = self.findPart(nums, target, False, targetIndex)
        return [leftLim, rightLim]

    def findPart(self, nums, target, isLeftPart, targetIndex):
        if isLeftPart:  # find left start index
            dwnLim = 0
            upLim = targetIndex
            lo = (dwnLim + upLim) >> 1
            while dwnLim <= upLim:
                if nums[lo] == target and lo >= 1 and nums[lo-1] != target:
                    return lo
                elif nums[lo] == target and lo == 0:
                    return lo
                elif nums[lo] == target and lo >= 1 and nums[lo-1] == target:
                    upLim = lo - 1
                else:  # nums[lo] < target
                    dwnLim = lo + 1
                lo = (upLim + dwnLim) >> 1
            return lo
        else:
            dwnLim = targetIndex
            upLim = len(nums) - 1
            hi = (dwnLim + upLim) >> 1
            while dwnLim <= upLim:
                if nums[hi] == target and hi < len(nums) - 1 and nums[hi+1] != target:
                    return hi
                elif nums[hi] == target and hi == len(nums) - 1:
                    return hi
                elif nums[hi] == target and hi < len(nums) - 1 and nums[hi+1] == target:
                    dwnLim = hi + 1
                else:
                    upLim = hi - 1
                hi = (upLim + dwnLim) >> 1
            return hi

    def findTarget(self, nums, target):
        lo = 0
        hi = len(nums) - 1
        mid = (lo + hi) >> 1
        while lo <= hi:
            if nums[mid] > target:
                hi = mid - 1
            elif nums[mid] < target:
                lo = mid + 1
            else:
                return mid
            mid = (hi + lo) >> 1
        return -1

if __name__ == "__main__":
    s = Solution()
    arr = [1,2,3,3,3,3,4,5,9]
    print(s.findTarget(arr, 3))
    print(s.searchRange(arr, 3))
