## 378. Kth Smallest Element in a Sorted Matrix

Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:


```
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
```

k = 8,

return 13.
Note: 
You may assume k is always valid, 1 ≤ k ≤ n2.

#### tips
第k大的问题不仅仅可以用堆（优先队列）来解决还可以使用 县排序再用索引去找 或者二分法查找的方法

这里因为这个题目的特殊性，使用了最小堆， 如果从最小堆中弹出的值是当前未弹出的值中最小的 那么弹出的第k个元素就是第k小 当然也可以用最大堆 把堆的长度设置为k 将所有的元素插入一遍 最后堆顶就是第k小的元素 但是这个方法在本题中会超时 根据矩阵的特性 先将 第一行的元素全部插入到堆中 因为第一个最小的元素必然在这些元素中产生，  
然后重复以下动作k次：
弹出堆中的元素 并且获得这个刚刚弹出的元素的 坐标， 然后将 这个元素的同一列的下一个元素放入堆中 

第k次弹出的元素即是所求。 因为每次弹出元素a[x][y]后 下一个最小的元素只能在 a[x][y+1] 中产生 这个画个图就明白了

至于二分法 就是看小于 mid的元素有多少 根据 这个指标来调整lo 和hi 这个是二维的情况

所以二分法不仅仅只能用来查找单一的元素还能干很多事情


#### mycode
```
import heapq
class Solution(object):
    def kthSmallest(self, matrix, k):
        """
        :type matrix: List[List[int]]
        :type k: int
        :rtype: int
        """
        r = 0
        c = 0
        if k == 1:
            return matrix[0][0]
        heap = []
        heapq.heapify(heap)
        for i in range(len(matrix[0])):
            heapq.heappush(heap, (matrix[0][i], 0, i))
        n = len(matrix)
        for j in range(k-1):
            top, topr, topc = heapq.heappop(heap)  # pop the smallest
            if topr == n - 1:
                continue
            heapq.heappush(heap, (matrix[topr+1][topc], topr+1, topc))
        return heapq.heappop(heap)[0]
```

