class Solution(object):
    def maxArea(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        left = 0
        right = len(height) - 1
        curProduct = min(height[left], height[right]) * (right - left)
        while left < right:
            tryProduct = min(height[left], height[right]) * (right - left)
            if tryProduct >= curProduct:
                curProduct = tryProduct
            if height[left] <= height[right]:
                left += 1
            else:
                right -= 1
        return curProduct

if __name__ == "__main__":
    s = Solution()
    arr = [1, 2, 4, 6, 5, 7, 8, 5, 2, 1]
    print(s.maxArea(arr))
