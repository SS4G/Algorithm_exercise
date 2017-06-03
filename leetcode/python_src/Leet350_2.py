class Solution(object):
    def intersect(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        nums1.sort()
        nums2.sort()        
        checked_list=[]
        k=0
        j=0
        num1_len=len(nums1)
        num2_len=len(nums2)
        less_len=num1_len if num1_len<num2_len else num2_len
        inter_section=[None]*less_len
        p=0
        if num1_len==0 or num2_len==0:
            return []       
        while k<num1_len :
            while nums2[j]<nums1[k]:#jump nums2[j]<nums1[k] situation
                j+=1
                if j>=num2_len or k>=num1_len:
                    del (inter_section[p:])            
                    return inter_section
            if nums2[j]>nums1[k]:#mismache nums1[k] in nums2[j]
                k+=1
            else:#nums
                inter_section[p]=nums1[k]
                k+=1
                j+=1
                p+=1
            print(k,j)
            if j>=num2_len or k>=num1_len:
                break        
        del (inter_section[p:])            
        return inter_section
a=[1,2]
b=[1,1]
s=Solution()
print(s.intersect(a,b))
