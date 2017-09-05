import re
class Solution(object):
    def calculate(self, s):
        """
        :type s: str
        :rtype: int
        """
        stack = []
        s = "".join([i for i in s if i != " "])
        splited = re.split("[\\+\\-\\*/]", s)
        oprands = [int(n) for n in splited]
        operators = []
        for i in s:
            if self.isOperators(i):
                operators.append(i)
        expression = []

        i = 0
        j = 0
        k = 0
        while j < len(oprands) or k < len(operators):
            if i & 0x01 == 0:
                if i >= 2:
                    if expression[-1] == '-':
                        expression[-1] = "+"
                        expression.append(-oprands[j])
                    else:
                        expression.append(oprands[j])
                else:
                    expression.append(oprands[j])
                j += 1
            else:
                expression.append(operators[k])
                k += 1
            i += 1

        while True:
            i = 0
            j = 0
            while j < len(expression):
                if len(stack) == 0:
                    stack.append(expression[j])
                elif self.isOperators(expression[j]):
                    stack.append(expression[j])
                else:
                    if stack[-1] == '*' or stack[-1] == "/":
                        op = stack.pop()
                        a = stack.pop()
                        b = expression[j]
                        if op == "*":
                            res = a * b
                        else:
                            if a >= 0 and b >= 0:
                                res = a//b
                            else:
                                res = -(abs(a)//b)
                        stack.append(res)
                    elif stack[-1] == '+' or stack[-1] == "-":
                        if j+1 >= len(expression) or (expression[j+1] not in "*/"):
                            op = stack.pop()
                            a = stack.pop()
                            b = expression[j]
                            stack.append(a + b if op == "+" else a - b)
                        else:
                            stack.append(expression[j])
                j += 1
            if len(stack) > 1:
                expression = stack
                print(expression)
                stack = []
            else:
                break
        return stack[0]

    def isOperators(self, s):
        if isinstance(s, int):
            return False
        return s in "+-*/"

if __name__ == "__main__":
    s = Solution()
    print(s.calculate("282-1*2*13-30-2*2*2/2-95/5*2+55+804+3024"))
    print(s.calculate("14-3/2"))
    print(3//2)
    print(-3//2)