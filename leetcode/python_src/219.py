class Solution(object):
    def containsNearbyDuplicate(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: bool
        """
        length=len(nums)
        if length<=1:
            return False
        save=[]
        for i in range(length):
            save.append([nums[i],i])
            #build a sorted list and save index at same time
        for i in range(length):
            for j in range(i,length):
                if save[i][0]>save[j][0]:
                    save[i],save[j]=save[j],save[i]
                elif save[i][0]==save[j][0]:
                    if save[i][1]>save[j][1]:
                        save[i],save[j]=save[j],save[i]
                        
                        
        for j in range(length-1):
            if save[j][0]==save[j+1][0] and (abs(save[j][1]-save[j+1][1])<=k):
                return True
                
        return False        
        
        
s=Solution()
s.containsNearbyDuplicate([4,5,6,2,5,3,9,0],4)