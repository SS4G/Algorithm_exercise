## 11. Container With Most Water

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.


#### tips
这个题目主要在于如何寻找最优

(x0, y0) (x1, y1)
容器的最大盛水量是 min(y0, y1) * abs(x0 - x1)
使用两个指针 一个头部一个尾部 如果左指针的高度比右指针的小 那么就将左指针向右移动 反之将右指针 移动 每次移动都计算当前容器的储水量， 如果比当前的最大值大则去替换

这个的原理如下， 假设最大的容器为 (xa, ya) (xb, yb)
根据移动的假设条件 会发现 一但一边到达xa 另一边必然会到达 xb 反之亦然 因为在循环结束前必然有一方会先到达xa 或者xb 之后另一个指针必然也会到达 最大容器的另一边 所以这个过程中最大容器的容量会被计算 必然会被保存。
ps 如果最大容器为 (xa,ya) (xb, yb) 那么 假设ya < yb 那么xb右侧的所有点的高度必然比ya要小(相等也不行) 否则 xb,yb 就不会是最大容器的右边界

有了这个点·画个图就可以找到这个o(n)的解法

#### mycode
```
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
```
