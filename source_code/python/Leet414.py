class Solution(object):
    def thirdMax(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums)<3:
            return max(nums)
        maxs=[-12147483648,-12147483648,-12147483648]
        for i in nums:
            if i==maxs[0] or i==maxs[1] or i==maxs[2]:
                continue
            if i >maxs[0]:
                maxs[2]=maxs[1]
                maxs[1]=maxs[0]
                maxs[0]=i
            elif i>maxs[1]:
                maxs[2] = maxs[1]
                maxs[1] = i
            elif i>maxs[2]:
                maxs[2] =i
        return maxs[2]
s=Solution()
print(s.thirdMax([1,1,1,1,2,3,4,5,6,7]))


