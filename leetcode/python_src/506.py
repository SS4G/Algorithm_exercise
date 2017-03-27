class Solution(object):
    def findRelativeRanks(self, nums):
        """
        :type nums: List[int]
        :rtype: List[str]
        """
        res_list=["Gold Medal", "Silver Medal", "Bronze Medal"]

        rank_dict={}
        index=0
        for score in nums:
            rank_dict[score]=index
            index += 1

        z = []
        z[:] = nums[:]
        z.sort()
        z.reverse()
        print(z)
        length=len(z)
        for i in range(min(3,len(nums))):
            nums[rank_dict[z[i]]] = res_list[i]

        for i in range(3,length):
            nums[rank_dict[z[i]]]=str(i+1)
        return nums

s=Solution()
print(s.findRelativeRanks([5, 4, ]))