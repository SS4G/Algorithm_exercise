## 553. Optimal Division
Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.

However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should NOT contain redundant parenthesis.

Example:

```
Input: [1000,100,10,2]
Output: "1000/(100/10/2)"
```

Explanation:
1000/(100/10/2) = 1000/((100/10)/2) = 200
However, the bold parenthesis in "1000/((100/10)/2)" are redundant, 
since they don't influence the operation priority. So you should return "1000/(100/10/2)". 

Other cases:

```
1000/(100/10)/2 = 50
1000/(100/(10/2)) = 50
1000/100/10/2 = 0.5
1000/100/(10/2) = 2
```

Note:

The length of the input array is [1, 10].
Elements in the given array will be in range [2, 1000].
There is only one optimal division for each test case.


#### TIPS
使用回溯法 每次递归的从一个表达式中取出优先级最高的那个除号 然后 根据 要求 看是是分子尽可能大 分母尽可能小 还是分子尽可能小 分母尽可能大 。。这个过程会是交替进行的

然后选出最大的结果 最终的字符串结果通过递归 自动加上层次括号

需要注意的是python2的浮点转化为字符串的结果的处理

#### mycode



```
class Solution(object):
    def optimalDivision(self, nums):
        """
        :type nums: List[int]
        :rtype: str
        """
        if len(nums) == 0:
            return ""
        nums = [float(i) for i in nums]
        res, str0 = self.findMostValue(nums, findMax=True)
        return str0

    def findMostValue(self, nums, findMax=True):
        if len(nums) == 1:
            str0 = ("x" + str(nums[0])).strip("0").strip(".") if "." in str(nums[0]) else "x" + str(nums[0])
            return nums[0], str0[1:]
        elif len(nums) == 2:
            str0 = ("x" + str(nums[0])).strip("0").strip(".") if "." in str(nums[0]) else "x" + str(nums[0])
            str1 = ("x" + str(nums[1])).strip("0").strip(".") if "." in str(nums[1]) else "x" + str(nums[1])
            return nums[0] / nums[1], str0[1:] + "/" + str1[1:]
        curMaxStr = ""
        curMinStr = ""
        curMax = -float("inf")
        curMin = float("inf")
        for i in range(len(nums) - 1):
            numrator = nums[:i + 1]
            deNumrator = nums[i + 1:]
            if findMax:
                num, numMark = self.findMostValue(numrator, findMax=True)
                deNum, deNumMark = self.findMostValue(deNumrator, findMax=False)
                tmpVal = num / deNum
                if tmpVal > curMax:
                    curMax = tmpVal
                    if "/" in deNumMark:
                        curMaxStr = numMark + "/" + "(" + deNumMark + ")"
                    else:
                        curMaxStr = numMark + "/" + deNumMark
            else:
                num, numMark = self.findMostValue(numrator, findMax=False)
                deNum, deNumMark = self.findMostValue(deNumrator, findMax=True)
                tmpVal = num / deNum
                if tmpVal < curMin:
                    curMin = tmpVal
                    if "/" in deNumMark:
                        curMinStr = numMark + "/" + "(" + deNumMark + ")"
                    else:
                        curMinStr = numMark + "/" + deNumMark
        if findMax:
            return curMax, curMaxStr
        else:
            return curMin, curMinStr
```
