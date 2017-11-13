## 42. Trapping Rain Water
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

![img](http://www.leetcode.com/static/images/problemset/rainwatertrap.png)

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

#### tips

可以使用暴力的 海平面上升法 即用一个海平面去上升 每次加上新被掩模的面积 但是这个复杂度接近O(mn) 所以大概率会超时

所以 应该使用 首尾指针 一个从前向后 一个从后向前 来计算 凹下去的面积

直到所有的面积都被计算过了

方法时这样的 
例如 
首先定位 lptr1 然后向左找到lptr2的位置 lptr2的值 必然大于lptr1的值 然后计算他们两之间的面积

同理从右侧也是 

lptr1 最后止步于 整体的最高点 lptr2 最后会滑出右边界 所以这个lptr2 滑出右边界的情况的面积不需要计算

这个方法尤其要注意的是 各种边界情况 比较头疼 比如有多个最高点的情形， 这届需要 使用大于号来终止一个区间 可能会遇到多个相等的极值 这个需要在过程中计算

![img](http://note.youdao.com/yws/public/resource/9bde0cfacaa1ff4a7a9ac75063b93c31/xmlnote/d453a0f8b73723a4a0c42f79bef897a2/22719)


#### mycode

```
public int trap(int[] height) {
        int area = 0;

        if (height.length < 2)
            return 0;

        int leftPtr = 0;
        int lastLeftLevelPtr = 0;
        int lastLeftLevel = height[0];
        int leftCounted = 0;

        int rightPtr = height.length - 1;
        int lastRightLevelPtr = height.length - 1;
        int lastRightLevel = height[height.length - 1];
        int rightCounted = height.length - 1;

        while (leftPtr < height.length) {
            while (leftPtr < height.length && height[leftPtr] <= lastLeftLevel) {
                if (height[leftPtr] == lastLeftLevel) {
                    area += calcAreaInterval(height, lastLeftLevel, lastLeftLevelPtr, leftPtr);
                    leftCounted = leftPtr;
                    lastLeftLevelPtr = leftPtr;
                }
                leftPtr++;
            }

            if (leftPtr < height.length) {
                area += calcAreaInterval(height, lastLeftLevel, lastLeftLevelPtr, leftPtr);
                leftCounted = leftPtr;
                lastLeftLevel = height[leftPtr];
                lastLeftLevelPtr = leftPtr;
            }
            //System.out.println("leftPtr:" + lastLeftLevelPtr + ":" + leftPtr + ":" + leftCounted);
        }

        while (rightCounted > leftCounted && rightPtr >= 0) {
            while (rightPtr >= 0 && height[rightPtr] <= lastRightLevel) {
                if (height[rightPtr] == lastLeftLevel) {
                    area += calcAreaInterval(height, lastRightLevel, rightPtr, lastRightLevelPtr);
                    rightCounted = rightPtr;
                    lastRightLevelPtr = rightPtr;
                }
                //System.out.println("JJ");
                rightPtr--;
            }
            //System.out.println("rightPtr:" + lastRightLevelPtr + ":" + rightPtr + ":" + rightCounted);
            if (rightPtr >= 0) {
                area += calcAreaInterval(height, lastRightLevel, rightPtr, lastRightLevelPtr);
                rightCounted = rightPtr;
                lastRightLevel = height[rightPtr];
                lastRightLevelPtr = rightPtr;
            }
        }

        return area;
    }

    public int calcAreaInterval(int[] height, int level, int st, int ed) {
        int area = 0;
        for (int i = st; i < ed; i++) {
            area += (level - height[i]) > 0 ? (level - height[i]) : 0;
        }
        return area;
    }
```
