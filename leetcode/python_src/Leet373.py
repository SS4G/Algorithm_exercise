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

if __name__ == "__main__":
    s = Solution()
    nums1 = [1, 2]
    nums2 = [3]
    print(s.kSmallestPairs(nums1, nums2, 3))
