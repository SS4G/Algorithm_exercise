## 241. Different Ways to Add Parentheses

Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".


```
((2-1)-1) = 0
(2-(1-1)) = 2
```

Output: [0, 2]


Example 2
Input: "2*3-4*5"

```
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
```

Output: [-34, -14, -10, -10, 10]

#### tips
这个问题的关键 是搞懂中缀表达式的优先级， 如果给一个中缀表达式中不同的符号指定不同的优先级， 那么 就和加了括号是等价的。所以使用递归来产生不同的优先级 可以从一堆符号中挑选一个优先级最高的 然后 把这个最高优先级的符号左右两边的的符号在递归的进行类似的优先级指定操作 最后得出一个全排列 然后把全排列在映射成对应的计算接过即可 这个映射的过程也是递归的。

#### mycode

```
import re
class Solution(object):
    def diffWaysToCompute(self, input):
        """
        :type input: str
        :rtype: List[int]
        """
        nums = re.split(string=input, pattern="[\+\-\*]")
        nums = [int(i) for i in nums]
        operators = re.split(string=input, pattern="\\d+")
        operators = [i for i in operators if len(i) > 0]


        types = self.assignPriority(operators)
        res = []
        for type in types:
            res.append(self.calculate(nums, operators, type))
        return res

    def calculate(self, nums, operators, priority):
        if len(operators) == 0 and len(nums) == 1:
            return nums[0]
        elif len(operators) == 1:
            op = operators[0]
            leftRes = nums[0]
            rightRes = nums[1]
        else:
            idx, val = self.getMaxIndex(priority)
            op = operators[idx]
            leftRes = self.calculate(nums[:idx + 1], operators[:idx], priority[:idx])
            rightRes = self.calculate(nums[idx + 1:], operators[idx + 1:], priority[idx + 1:])
        if op == "+":
            return leftRes + rightRes
        elif op == "-":
            return leftRes - rightRes
        elif op == "*":
            return leftRes * rightRes
        else:
            assert False, "wtf?"
            return 0

    def getMaxIndex(self, li):
        maxval = -0xfffffff
        maxIndex = -1
        for i in range(len(li)):
            if maxval <= li[i]:
                maxval = li[i]
                maxIndex = i
        return maxIndex, maxval

    def assignPriority(self, operators):
        dprec = {}
        return self.assignPriorityRecursive(len(operators), dprec)

    def assignPriorityRecursive(self, opsLen, recDict):
        if opsLen == 0:
            return [[]]
        if opsLen in recDict:
            return recDict[opsLen]
        res = []
        for i in range(opsLen):
            left = self.assignPriorityRecursive(i, recDict)
            right = self.assignPriorityRecursive(opsLen - (i + 1), recDict)
            for l in left:
                for r in right:
                    tmpRes = l[:] + [opsLen, ] + r[:]
                    res.append(tmpRes)
        recDict[opsLen] = res
        return res
```
