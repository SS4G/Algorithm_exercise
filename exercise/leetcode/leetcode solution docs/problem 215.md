## 215. Kth Largest Element in an Array Add to List

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

#### tips
使用大小为k的小顶堆即可
一个大小为k的小顶堆相当于就是一个大小为k的优先队列
最大的元素排在最前面 第k小的排在最后 但我们只关心最小的元素 所以使用小顶堆即可
#### mycode
```Python
class Solution(object):
    def findKthLargest(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        minHeap = [-(2 << 32), ]*k
        for n in nums:
            self.insertToMinHeap(minHeap, n)
            #print(minHeap)
        return minHeap[0]

    def getLeftChildIndex(self, me):
        return (me << 1) + 1

    def getRightChildIndex(self, me):
        return (me << 1) + 2

    def insertToMinHeap(self, heap, val):
        if val <= heap[0]:
            return
        else:
            heap[0] = val
            ptr = 0
            heapSize = len(heap)
            leftIndex = self.getLeftChildIndex(ptr)
            rightIndex = self.getRightChildIndex(ptr)
            while True:
                if leftIndex >= heapSize:
                    break
                elif rightIndex >= heapSize:
                    minIndex = leftIndex
                else:
                    minIndex = leftIndex if heap[leftIndex] <= heap[rightIndex] else rightIndex
                if heap[ptr] > heap[minIndex]:
                    tmp = heap[ptr]
                    heap[ptr] = heap[minIndex]
                    heap[minIndex] = tmp
                    ptr = minIndex
                else:
                    break
                leftIndex = self.getLeftChildIndex(ptr)
                rightIndex = self.getRightChildIndex(ptr)
```
