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

if __name__ == "__main__":
    s = Solution()
    matrix = [
                 [1, 5, 9],
                 [10, 11, 13],
                 [12, 13, 15],
             ]
    k = 8
    # heap = s.initHeap(9)
    # for i in range(20):
        # s.insertToHeap(heap, i)
    #print(heap)
    print(s.kthSmallest(matrix, k))