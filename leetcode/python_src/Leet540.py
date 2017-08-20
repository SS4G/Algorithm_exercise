class Solution(object):
    def singleNonDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        lo = 0
        hi = len(nums) - 1
        while lo < hi:
            mid = (lo + hi) >> 1
            curVal = nums[mid]
            if mid % 2: # odd index
                if nums[mid - 1] != curVal:
                    hi = mid - 1
                else:
                    lo = mid + 1
            else: # even index
                if nums[mid + 1] != curVal:
                    hi = mid
                else:
                    lo = mid + 1
        # print(nums[lo])
        # print(nums[hi])
        return  nums[lo]

if __name__ == "__main__":
    s = Solution()
    arr = [1, 1, 2, 2, 3, 4, 4, 5, 5]
    arr = [1, 1, 2, 3, 3]
    print(s.singleNonDuplicate(arr))
