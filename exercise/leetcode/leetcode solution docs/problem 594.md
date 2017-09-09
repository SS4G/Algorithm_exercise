## 594. Longest Harmonious Subsequence My SubmissionsBack To Contest

We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.

Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.

Example 1:

```
Input: [1,3,2,2,5,2,3,7]
Output: 5
```

Explanation: The longest harmonious subsequence is [3,2,2,2,3].
#### Note: 
The length of the input array will not exceed 20,000.

#### tips
注意此处的子序列可以不连续 比如上面给出的例子

#### mycode

```Python
class Solution(object):
    def findLHS(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        numDict = {}
        for i in nums:
            if i not in numDict:
                numDict[i] = 1
            else:
                numDict[i] += 1

        keys = list(numDict.keys())
        keys.sort()
        maxL = 0
        for i in range(len(keys)-1):
            if keys[i+1] - keys[i] == 1:
                nowL = numDict[keys[i]] + numDict[keys[i+1]]
                if nowL > maxL:
                    maxL = nowL
        return maxL

if __name__ == "__main__":
    s = Solution()
    print(s.findLHS([1,3,2,2,5,2,3,7]))
```
