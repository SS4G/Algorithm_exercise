class Solution(object):
    def kthSmallest(self, matrix, k):
        """
        :type matrix: List[List[int]]
        :type k: int
        :rtype: int
        """
        r = 0
        c = 0
        heap = self.initHeap(k)
        while r < len(matrix):
            while c < len(matrix[0]):
                self.insertToHeap(heap, matrix[r][c])
                c += 1
            r += 1
            c = 0
            """
            if matrix[r][c] < heap[0]:
                self.insertToHeap(heap, matrix[r][c])
                print(heap)
                r += 1
                c += 1
            else:
                r += 1
                c = 0
            """
        return heap[0]

    def getLeft(self, thisIndex):
        return (thisIndex << 1) + 1

    def getRight(self, thisIndex):
        return (thisIndex << 1) + 2

    def initHeap(self, size):
        return [0xffffffff, ]*size

    def insertToHeap(self, heap, newVal):
        if newVal < heap[0]:
            heap[0] = newVal
            thisIndex = 0
            while thisIndex < len(heap):
                if self.getLeft(thisIndex) >= len(heap):
                    break
                elif self.getRight(thisIndex) >= len(heap):
                    if heap[thisIndex] < heap[self.getLeft(thisIndex)]:
                        heap[thisIndex], heap[self.getLeft(thisIndex)] = heap[self.getLeft(thisIndex)], heap[thisIndex]
                    break
                else:
                    if heap[thisIndex] > max(heap[self.getLeft(thisIndex)], heap[self.getRight(thisIndex)]):
                        break
                    else:
                        if heap[self.getLeft(thisIndex)] > heap[self.getRight(thisIndex)]:
                            heap[thisIndex], heap[self.getLeft(thisIndex)] = heap[self.getLeft(thisIndex)], heap[thisIndex]
                            thisIndex = self.getLeft(thisIndex)
                        else:
                            heap[thisIndex], heap[self.getRight(thisIndex)] = heap[self.getRight(thisIndex)], heap[thisIndex]
                            thisIndex = self.getRight(thisIndex)

if __name__ == "__main__":
    s = Solution()
    matrix = [
                 [1, 5, 9],
                 [10, 11, 13],
                 [12, 13, 15]
             ]
    k = 8
    # heap = s.initHeap(9)
    # for i in range(20):
        # s.insertToHeap(heap, i)
    #print(heap)
    print(s.kthSmallest(matrix, k))