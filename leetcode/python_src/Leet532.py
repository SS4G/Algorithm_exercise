class Solution(object):
    def findPairs(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        nums2 = nums #.copy()
        set0 = set(nums2)
        res = 0
        if k == 0:
            num_dict = {}
            for i in nums2:
                if i not in  num_dict:
                    num_dict[i] = 1
                else:
                    num_dict[i] += 1

            for i in set0:
                if num_dict[i]>=2:
                    res += 1
        elif k < 0:
            return 0
        else:
            for i in set0:
                if i+k in set0:
                    res += 1
        return res

s = Solution()
print(s.findPairs([3, 1, 4, 1, 5], 2))
print(s.findPairs([1, 2, 3, 4, 5], 1))
print(s.findPairs([1, 3, 1, 5, 4], 0))