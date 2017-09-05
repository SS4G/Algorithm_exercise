## 22. Generate Parentheses
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:


```
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```

#### tips
这个可以使用递归来做
验证一个括号匹配式可以用堆栈来验证他是否匹配
可以用回溯法一边生成一边用堆栈去检验，但是这样做会有些麻烦  
生成合法的括号匹配式可以 这样做。

```
if leftParentheses < N: # cond1
    add leftParentheses
if leftParentheses < rightParenteses: # cond2
    add rightParenteses
```
也就是说 在cond1成立时 可以添加左括号
在cond2成立时 可以添加右括号
所以这样可以从一个空字符串 逐渐递归的的生成所有有效的解

具体见代码 这样保证所有生成的解都是有效的 而不用去回溯

#### mycode

```
class Solution(object):
    def generateParenthesis(self, n):
        result = []
        self.addParenthesis(result, "", 0, 0, n)
        return result

    def addParenthesis(self, res, partRes, openP, closeP, n):
        if len(partRes) == n << 1:
            res.append(partRes)
            return
        if openP < n:
            self.addParenthesis(res, partRes+"(", openP+1, closeP, n)
        if closeP < openP:
            self.addParenthesis(res, partRes + ")", openP, closeP+1, n)
```
