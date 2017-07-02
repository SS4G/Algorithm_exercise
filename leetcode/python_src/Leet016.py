class Solution(object):
    def threeSumClosest(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        nums.sort()
        res = 0xffffffff
        for i in range(len(nums) - 2):
            il = i + 1
            ih = len(nums) - 1
            subTarget = target - nums[i]
            while il < ih:
                if il != i and ih != i:
                    s = nums[il] + nums[ih] + nums[i]
                    if abs(s - target) < abs(res - target):
                        res = s
                if nums[il] + nums[ih] < subTarget:
                    il += 1
                elif nums[il] + nums[ih] == subTarget:
                    break
                else:
                    ih -= 1
        return res

if __name__ == "__main__":
    s = Solution()
    nums = [-1, 2, 1, -4]
    print(s.threeSumClosest(nums, 1))

