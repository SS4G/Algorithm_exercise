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
    print(-1//6)
    print(-7//2)
    print(-7//-2)
