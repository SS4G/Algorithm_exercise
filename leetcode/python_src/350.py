class Solution(object):
    def intersect(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        nums1.sort()
        nums2.sort()
        inter_section=[]
        checked_list=[]
        for k in nums1:
            try:
                nums2.index(k)
            except ValueError:
                continue 
            else:
                if k not in checked_list:
                    checked_list.append(k)
                    cnt1=nums1.count(k)
                    cnt2=nums2.count(k)
                    less=cnt1 if cnt1<cnt2 else cnt2
                    print("less")
                    print(less)
                    inter_section.extend([k]*less) 
                    print(inter_section)  
                
        return inter_section
        
        
[1,2,2,3,4,5,6]
[3,2,3,2,2,1,5,6,7]