class Solution(object):
    def arrayNesting(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        marked = [False, ]*len(nums)
        res = 0
        for i in range(len(nums)):
            if marked[i]:
                continue
            else:
                marked[i] = True
                nextI = nums[i]
                length = 1
                while nextI != i:
                    marked[nextI] = True
                    nextI = nums[nextI]
                    length += 1
                res = res if res > length else length
        return res
if __name__ == "__main__":
    s = Solution()
    A = [5, 4, 0, 3, 1, 6, 2]
    print(s.arrayNesting(A))