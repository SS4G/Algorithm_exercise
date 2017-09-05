import random
class Solution(object):
    def __init__(self, nums):
        """
        :type nums: List[int]
        :type numsSize: int
        """
        self.nums = nums

    def pick(self, target):
        """
        :type target: int
        :rtype: int
        """
        cnt = 0
        flag = False
        r = -1
        for i in range(len(self.nums)):
            if self.nums[i] == target:
                if flag is False:
                    flag = True
                    r = i
                cnt += 1
                r = i if random.randint(1, cnt) == 1 else r
        return r

        # Your Solution object will be instantiated and called as such:
        # obj = Solution(nums)
        # param_1 = obj.pick(target)