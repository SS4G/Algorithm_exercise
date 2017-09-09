class Solution(object):
    def intersection(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        nums1_uni=[]
        nums2_uni=[]
        nums_uni=[]
        for i in range(len(nums1)):
            if nums1[i] not in nums1_uni:
                nums1_uni.append(nums1[i])
        print("-----1-----")
        print(nums1_uni)
        
        for i in range(len(nums2)):
            if nums2[i] not in nums2_uni:
                nums2_uni.append(nums2[i])
        print("-----2-----")
        print(nums2_uni)
        
        
        for i in range(len(nums1_uni)):
            if nums1_uni[i] in nums2_uni:
                nums_uni.append(nums1_uni[i])
        
        return nums_uni
        
nums1 = [1, 2, 2, 1]
nums2 = [2, 2]   
a=Solution()
print(a.intersection(nums1, nums2))