class Solution(object):
    def majorityElement(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        c0 = 0
        a0 = None
        c1 = 0
        a1 = None
        for n in nums:
            if a0 is not None and n == a0:
                c0 += 1
                continue

            if a1 is not None and n == a1:
                c1 += 1
                continue

            if a0 is None:
                a0 = n
                c0 = 1
                continue

            if a1 is None:
                a1 = n
                c1 = 1
                continue

            c0 -= 1
            c1 -= 1
            if c0 == 0:
                a0 = None
            if c1 == 0:
                a1 = None
        res = []
        if a0 is not None and nums.count(a0) > len(nums)//3:
            res.append(a0)
        if a1 is not None and nums.count(a1) > len(nums)//3:
            res.append(a1)
        return res

if __name__ == "__main__":
    s = Solution()
    nums = [4,2,1,1]
    res = s.majorityElement(nums)
    print(res)