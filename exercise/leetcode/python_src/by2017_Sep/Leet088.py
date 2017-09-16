class Solution(object):
    def merge(self, nums1, m, nums2, n):
        """
        :type nums1: List[int]
        :type m: int
        :type nums2: List[int]
        :type n: int
        :rtype: void Do not return anything, modify nums1 in-place instead.
        """
        nums1[m:n+m]=nums2[0:n]
        nums1.sort()
        return 
        
s=Solution()
nums1=[2,0]
m=1
nums2=[1]
n=1
s.merge(nums1, m, nums2, n)
print(nums1)