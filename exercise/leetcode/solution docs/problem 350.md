# leetcode 350
## Qusetion:
#### Intersection of Two Arrays II
Given two arrays, write a function to compute their intersection.

##### Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

##### Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
##### Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

## Answer:
#### method 1 350_1.py

1. 选定一个参数序列 nums1
2. 使用for迭代nums1的元素 x
3. 查找x 是否也在nums2 中 若存在 使用count计算出x在这两个序列中的个数 取最小值 并将x加入checked_list 防止x在后续被重复查找
4. 直到nums1 的元素全部被迭代完 中间需要注意使用index(x)可能引发 ValueError 异常 需要处理

###### PS：该方法效率较低 index查找运算和count可能是瓶颈

#### method 2 350_2.py
采用索引的方式使用多重循环查找 见代码

#### 不同方法的效率比较
src|Status	|  Run Time	   |Language
---|--------|--------------|------
350_1.py|Accepted|	60 ms	   |python
350_2.py|Accepted|	120 ms	   |python

##### 附代码：

```Python
#350_1.py
class Solution(object):
    def intersect(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        nums1.sort()#sort here is useless
        nums2.sort()#sort here is useless
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
                    inter_section.extend([k]*less) 
        return inter_section
```

```Python
#350_2.py
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
```

