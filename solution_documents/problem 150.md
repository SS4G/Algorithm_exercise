## 150. Evaluate Reverse Polish Notation

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:

```
["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
```


#### tips
这个题目就是逆波兰表达式 也就是后缀表达式的求值 使用教科书上标准的堆栈来求即可
但是这里要注意的是python的负数的整除

Python
-1/6 = -1 而不是0 这与期望的0 不符
    print(-1//6)  -> -1
    print(-7//2)  -> -4
    print(-7//-2) -> 3
    
所以要用期望的绝对值来处理一下


#### mycode
```
class Solution(object):
    def evalRPN(self, tokens):
        """
        这个题目就是求后缀表达式 也称为 逆波兰表达式
        :type tokens: List[str]
        :rtype: int
        """
        stack = []
        for i in tokens:
            if i not in "+-*/":
                stack.append(int(i))
            else:
                b = stack.pop()
                a = stack.pop()
                if i == "+":
                    res = a + b
                elif i == "-":
                    res = a - b
                elif i == "*":
                    res = a * b
                elif i == "/":
                    res = abs(a)//abs(b)
                    if (a < 0 <= b) or (b < 0 <= a):
                        res = -res
                stack.append(res)
        return stack[0]

if __name__ == "__main__":
    s = Solution()
    tokens0 = ["6", "2", "/"]
    tokens1 = ["2", "1", "+", "3", "*"]
    tokens2 = ["4", "13", "5", "/", "+"]
    tokens3 = ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
    tokens4 = ["4", "-2", "/", "2", "-3", "-", "-"]
    # print(s.evalRPN(tokens0))
    # print(s.evalRPN(tokens1))
    print(s.evalRPN(tokens4))
    print(s.evalRPN(tokens3))
```
