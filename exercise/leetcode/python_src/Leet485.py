class Solution(object):
    def findMaxConsecutiveOnes(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        maxs=[]
        last=0
        current=0
        for i in nums:
            current+=i
            if current>last:
                last=current
                continue
            else:
                maxs.append(current)
                last=0
                current=0
        maxs.append(current)
        return max(maxs)
        
        
s=Solution()
print(s.findMaxConsecutiveOnes([1,1,0,1,1,1]))