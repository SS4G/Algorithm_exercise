## 385. Mini Parser

Given a nested list of integers represented as a string, implement a parser to deserialize it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Note: You may assume that the string is well-formed:

String is non-empty.
String does not contain white spaces.
String contains only digits 0-9, [, - ,, ].
Example 1:


```
Given s = "324",
```


You should return a NestedInteger object which contains a single integer 324.  
Example 2:
```
Given s = "[123,[456,[789]]]",
```
Return a NestedInteger object containing a nested list with 2 elements:

1. An integer containing value 123.
2. A nested list containing two elements:
    i.  An integer containing value 456.
    ii. A nested list with one element:
         a. An integer containing value 789.

#### tips
每当遇到一个‘[’ 就创建一个空的list对象添加到当前的list内 然后将当前的指针移动到这个新的对象内 并用兑现记住外层的list对象 如果是数字就直接添加到当前的list对象内 如果遇到']' 就跳出到之前堆栈中的第一个list对象内 

最后记得检查最外层的list是否里面只有一个元素 如果是 就返回这一个元素 因为最外层的list是 额外加上的

#### mycode
```Python
class Solution(object):
    def deserialize(self, s):
        """
        :type s: str
        :rtype: NestedInteger
        """
        if s == "":
            return NestedInteger([])
        res = self.parser(s)
        return NestedInteger(res[0]) if len(res) == 1 else NestedInteger(res)

    def parser(self, str):
        i = 0
        digFlag = False
        rootResult = []
        tmpResult = rootResult
        stack = []
        while i < len(str):
            if (self.isDigit(str[i])) and digFlag is False:
                digFlag = True
                st = i
            elif not self.isDigit(str[i]) and digFlag is True:
                end = i
                val = int(str[st: end])
                tmpResult.append(val)
                digFlag = False
                if str[i] == ']':
                    tmpResult = stack.pop()
            elif str[i] == '[':
                tmpResult.append([])
                stack.append(tmpResult)
                tmpResult = tmpResult[-1]
            elif str[i] == ']':
                tmpResult = stack.pop()

            i += 1
        if digFlag is True:  # special situation “324”
            val = int(str[st: ])
            tmpResult.append(val)

        return rootResult

    def isDigit(self, c):
        return ord('0') <= ord(c) <= ord('9') or c == '-'
```
