## 373. Find K Pairs with Smallest Sums

You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:

```
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]
```


The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

```
Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

Return: [1,1],[1,1]
```


The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:

```
Given nums1 = [1,2], nums2 = [3],  k = 3 

Return: [1,3],[2,3]
```


All possible pairs are returned from the sequence:
[1,3],[2,3]

#### tips
可以设置两个指针 ptr1 ptr2 分别指向nums1 和nums2 
然后 ptr1 每次向后移动一个位置 如果 nums1[ptr1] + nums2[ptr2] > 当前的第k小值 就将 ptr2 向后移动一位 ptr1 置位0 知道 全部元素迭代完毕 或者 nums2[ptr2] + nums1[ptr1] > 当前的第k小值
迭代结束

至于第k小值 的实现 使用一个最大堆 这个最大堆的堆顶即为 该值 如果新的值比堆顶小 那么就用新的值去替换 堆顶

- 需要注意的是 python的 heapq 只提供了最小堆  所以在想使用最大堆的时候 应该在插入元素时将元素用于排序的key值取反 这样就可以当做最大堆使用   
- 另外一个需要注意的是 heapq 的pushpop 是在堆顶 所以获取的是最小值 至于插入嘛 既然是优先队列 插入在哪里无所谓


#### mycode

```
import heapq
class Solution(object):
    def kSmallestPairs(self, nums1, nums2, k):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :type k: int
        :rtype: List[List[int]]
        """
        if k == 0 or len(nums1) == 0 or len(nums2) == 0:
            return []

        heap = []
        heapq.heapify(heap)
        p1 = 0
        p2 = 0
        for i in range(k):  # 首先push k个 元素
            sum = nums1[p1] + nums2[p2]
            element = (-sum, nums1[p1], nums2[p2])
            heapq.heappush(heap, element)
            if p1 < len(nums1) - 1:
                p1 += 1
            elif p1 == len(nums1) - 1 and p2 < len(nums2) - 1:
                p2 += 1
                p1 = 0
            else:  # total sum type less than k
                return [[e[1], e[2]] for e in heap]

        while True:
            sum = nums1[p1] + nums2[p2]
            if sum > (-heap[0][0]):
                if p1 == 0:
                    break
                p2 += 1
                p1 = 0
                if p2 >= len(nums2):
                    break
                continue
            else:
                element = (-sum, nums1[p1], nums2[p2])
                heapq.heappop(heap)
                heapq.heappush(heap, element)
                p1 += 1
                if p1 == len(nums1):
                    p1 = 0
                    p2 += 1
                    if p2 >= len(nums2):
                        break

        return [[e[1], e[2]] for e in heap]
```

