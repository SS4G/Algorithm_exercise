## 625. Minimum Factorization

Given a positive integer a, find the smallest positive integer b whose multiplication of each digit equals to a.

If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.

Example 1

```
Input:

48 
Output:
68
```

Example 2

```
Input:

15
Output:
35
```

#### tips
尝试用回溯法 把一个数分解为 10 以内的因数 如果获得到一个符合的结果 就把这个结果添加到output当中

回溯法过程中需要有这么几个限制条件 来保证回溯的快速进行 

因数分解要从 2~9 
从大的开始分 
要给每次递归设置上界 比如 上次的因数分解是8 那么这次的分解就只能小于等于8
防止出现 8,9,7， 和 7,9,8等重复结果

注意藤树情况1的处理

#### mycode

```
class Solution(object):
    def smallestFactorization(self, a):
        """
        :type a: int
        :rtype: int
        """
        if a == 1:
            return 1
        elif a == 0:
            return 0

        listdiv = (9, 8, 7, 6, 5, 4, 3, 2)
        stack = []
        output = []
        minLen = [12]
        self.splitWithIn10(a, stack, 0, minLen, listdiv, output, 9)
        if len(output) == 0:
            return 0

        for i in range(len(output)):
            output[i].sort()

        strRes = ["".join([str(d) for d in o]) for o in output]
        intRes = min([int(s) for s in strRes])
        if intRes > 0x7fffffff:
            return 0
        else:
            return intRes


    def splitWithIn10(self, num, stack, currentLength, minLegthRes, listDiv, output, limit):
        if currentLength > minLegthRes[0]:
            return
        for div in listDiv:
            if div <= limit:
                if num % div == 0:
                    stack.append(div)
                    if num//div == 1:
                        if len(stack) <= minLegthRes[0]:
                            output.append(stack[:])
                            minLegthRes[0] = len(stack)
                    else:
                        self.splitWithIn10(num//div, stack, currentLength + 1, minLegthRes, listDiv, output, div)
                    stack.pop()
            else:
                pass
        return
```
