## 640. Solve the Equation
Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.

If there is no solution for the equation, return "No solution".

If there are infinite solutions for the equation, return "Infinite solutions".

If there is exactly one solution for the equation, we ensure that the value of x is an integer.


```
Example 1:
Input: "x+5-3+x=6+x-2"
Output: "x=2"
Example 2:
Input: "x=x"
Output: "Infinite solutions"
Example 3:
Input: "2x=x"
Output: "x=0"
Example 4:
Input: "2x+3x-6x=x+2"
Output: "x=-1"
Example 5:
Input: "x=x+2"
Output: "No solution"
```

#### tips
解一元一次方程 重点是怎么 切分出各个项 可以使用正则表达式来完成切分。
然后使用元组合并同类型，变量 
这里说一个小技巧， 吧所有的情况标准化 比如 一边等式的开头如果什么符号都没有就给他加一个正号，这样就方便后续统一处理。这种预处理的方式还有使用哑节点的链表， 可以使得处理更加的统一，代码更加的简洁

#### mycode

```
import re


class Solution:
    def solveEquation(self, equation):
        """
        :type equation: str
        :rtype: str
        """
        sp = equation.split("=")
        leftEquation = sp[0]
        rightEquation = sp[1]
        if leftEquation[0] not in "+-":
            leftEquation = "+" + leftEquation
        if rightEquation[0] not in "+-":
            rightEquation = "+" + rightEquation

        elementPat = re.compile("[\\+\\-]\\d*x?")
        leftElement = elementPat.findall(leftEquation)
        rightElement = elementPat.findall(rightEquation)
        leftMerge = [0, ] * 2  # [c, x]
        rightMerge = [0, ] * 2
        for e in leftElement:
            e0 = self.transform(e)
            if e0[1] == "x":
                leftMerge[1] += e0[0]
            else:
                leftMerge[0] += e0[0]

        for e in rightElement:
            e0 = self.transform(e)
            if e0[1] == "x":
                rightMerge[1] += e0[0]
            else:
                rightMerge[0] += e0[0]

        if leftMerge[1] == rightMerge[1]:
            return "Infinite solutions" if leftMerge[0] == rightMerge[0] else "No solution"
        else:
            leftMerge[1] -= rightMerge[1]
            rightMerge[0] -= leftMerge[0]
            return "x=" + str(rightMerge[0] // leftMerge[1])

    def transform(self, s0):
        negFlag = True if s0[0] == "-" else False
        if "x" in s0:
            typex = "x"
            if len(s0) == 2:
                coe = 1
            else:
                coe = int(s0[1:len(s0) - 1])
        else:
            typex = "c"  # const value
            coe = int(s0[1:])
        coe = -coe if negFlag else coe
        return coe, typex  # (coe, type)

if __name__ == "__main__":
    s = Solution()
    equ = "x+5-3+x=6+x-2"
    equ = "x=3x"
    equ = "2x+3x-6x=x+2"
    equ = "x=x+2"
    equ = "5=6"
    print(s.solveEquation(equ))
```
