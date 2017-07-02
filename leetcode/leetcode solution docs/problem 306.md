## 306. Additive Number
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
1 + 99 = 100, 99 + 100 = 199  


Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Follow up:
How would you handle overflow for very large input integers?

#### tips
使用回溯法去尝试向后搜索进行判断 如果当前无法推进 可以及时的对搜索树进行剪枝处理  其实这个也有点像动态规划 不断的根据当前将问题分解为子问题去求解

#### mycode

```
class Solution(object):
    def isAdditiveNumber(self, num):
        """
        :type num: str
        :rtype: bool
        """
        if num == "":
            return False
        return self.validRecursive(0, num, -1, matching=False)

    def validRecursive(self, startIndex, num, lastVal, matching=True):
        for endIndex in range(startIndex + 1, len(num) + 1):
            thisVal = int(num[startIndex:endIndex])
            if thisVal < 10 and endIndex - startIndex >= 2:
                return False

            if not matching:
                nextState = self.validRecursive(endIndex, num, thisVal)
                if nextState:
                    return True
            else:
                nextVal = lastVal + thisVal
                nextStr = str(nextVal)
                if nextStr == num[endIndex:endIndex+len(nextStr)]:  # match success
                    if endIndex < len(num) - len(nextStr):
                        nextState = self.validRecursive(endIndex, num, thisVal)
                        if nextState:
                            return True
                    else:
                        return True
                else:
                    continue
        return False
```
