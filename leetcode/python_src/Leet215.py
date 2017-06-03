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


if __name__ == "__main__":
    s = Solution()
    nums = [3, 2, 1, 5, 6, 4]
    k = 2
    print(s.findKthLargest(nums, k))
    nums = [3, 2, 1, 5, 6, 4, 8, 8, 8]
    k = 7
    print(s.findKthLargest(nums, k))
    nums = [1, 2, 2, 2, 5]
    k = 3
    print(s.findKthLargest(nums, k))
