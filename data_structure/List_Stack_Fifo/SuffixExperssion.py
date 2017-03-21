# coding=utf-8
# Author: SS4G
# Date 2017/03/15
# all test case passed


class SuffixExpression:
    """
    计算后缀表达式
    将中缀表达式转换为后缀表达式
    只处理 加减乘除 以及一位正整数 其他的暂时不管
    """
    def __init__(self):
        pass

    def calculate(self, operand0, operand1, opcode):
        """
        单纯的计算
        :param operand0:
        :param operand1:
        :param opcode:
        :return:
        """
        if opcode == "+":
            return operand0+operand1
        elif opcode == "-":
            return operand0-operand1
        elif opcode == "*":
            return operand0*operand1
        elif opcode == "/":
            return operand0/operand1

    def is_digits(self, s):
        return s in "0123456789"

    def is_opcode(self, s):
        return s in "+-*/"

    def calc_suffix(self, expression):
        """
        认为传入的后缀表达式是正确的
        calculate the suffix expression
        计算后缀表达式
        :param expression:
        :return:
        """
        op_num_stack = []
        for i in expression:
            if self.is_digits(i):
                op_num_stack.append(int(i))
            elif self.is_opcode(i):
                op1 = op_num_stack.pop()
                op0 = op_num_stack.pop()
                op_num_stack.append(self.calculate(op0, op1, i))
        return op_num_stack[0]

    def op_code_cmp(self, op0, op1):
        """
        比较操作符优先级
        :param op0:
        :param op1:
        :return: positive int if op0 > op1
                 zero  if op0 == op1
                 negtive int if op0 < op1
        """
        if (op0[1] > op1[1]) or (op0[1] == op1[1] and op0[2] > op1[2]):
            return 1
        if (op0[1] == op1[1]) and (op0[2] == op1[2]):
            return 0
        if (op0[1] < op1[1]) or (op0[1] == op1[1] and op0[2] < op1[2]):
            return -1

    def midfix2suffix(self, midfix_exp):
        """
        将中缀表达式转化为后缀表达式
        :param midfix_exp:
        :return:
        """
        base_rank = 0  # 当前的括号优先级
        # +- 优先级为1 */优先级为2
        # 实际的优先级为 基础优先级加上 括号优先级 嵌套的越深优先级越高
        opcode_stack = []
        opcode_array = []
        oprand_array = []
        output = []
        for i in midfix_exp:
            if i == "(":
                base_rank += 1
            elif i == ")":
                base_rank -= 1
            elif i == "+" or i == "-":
                opcode_array.append((i, base_rank, 0))
            elif i == "*" or i == "/":
                opcode_array.append((i, base_rank, 1))
            else:
                oprand_array.append(i)

        # print(opcode_array) # 查看操作符的优先级

        output.append(oprand_array[0])
        output.append(oprand_array[1])
        opcode_stack.append(opcode_array[0])
        i = 1  # 将要压入的操作符的索引 该索引对应的值尚未压入

        while (len(opcode_stack) > 0) or (i <= len(opcode_array)-1):
            # 操作符所处的位置不是在操作符的列表中就是在操作符的堆栈里
            # 所以只有这两个地方都没有操作符了才能说明所有的操作符都被输出到了输出流里面
            if i <= len(opcode_array)-1:  # 仍然有操作符在操作符列表中
                if len(opcode_stack) > 0:  # 如果当前堆栈中有之前的操作符 就要拿现在的操作符和之前的操作符去比较
                    # 如果之前的操作符的优先级更高就要保证之前的操作符先输出 然后才能将后面的操作数放入到输出流中
                    cmp_res = self.op_code_cmp(opcode_stack[-1], opcode_array[i])  # 将当前的运算符与后面的一个运算符比较优先级
                    if cmp_res >= 0:
                        output.append(opcode_stack.pop()[0])  # 将优先级大的操作符输出
                    else:  # 若当前的操作符更大那么将操作数放入输出中 操作符放入堆栈中 因为还不知道下一个操作符的优先级是否更高
                        opcode_stack.append(opcode_array[i])
                        output.append(oprand_array[i + 1])
                        i += 1
                else:
                    opcode_stack.append(opcode_array[i])
                    output.append(oprand_array[i+1])
                    i += 1
            else:  # 若所有操作符都被检索过了 只在操作符堆栈中还有操作符 那么就说明只可能在堆栈中还有操作符将他们按照次序弹出来就好
                output.append(opcode_stack.pop()[0])
        return "".join(output)

# Test

if __name__ == "__main__":
    s = SuffixExpression()
    testcases0 = [
        ("12*", 2),  # 1*2
        ("12*65+*", 22),  # 1*2*(6+5)
        ("123*+45*67+*+", 267),  # 1+2*3+4*5*(6+7)
    ]
    for testcase in testcases0:
        assert s.calc_suffix(testcase[0]) == testcase[1], "suffix calc error"

    testcases1 = [
        ("1+2*3+4-7*8-9", "123*+4+78*-9-"),
        ("1+2*(5*7+9)+2", "1257*9+*+2+"),
    ]

    for testcase in testcases1:
        try:
            assert s.midfix2suffix(testcase[0]) == testcase[1], "midfix to suffix error"
        except AssertionError:
            print("ERROR! ")
            print("return ", s.midfix2suffix(testcase[0]))
            print("require", testcase[1])

    testcase2 = [
        ("1+2*3+4+5*6+7+8+9", 65),
        ("1+2*(3+4)+(5*6)+(7+8+9)", 69),
        ("1+2*(3*4+5)*6+(7+8*9)", 284),
        ("(1+2)*3+(4+5)*(6+7)+8+9", 143),
        ("2+2", 4),
        ("3*3", 9),
        ("(((3+3)))", 6),
    ]
    for testcase in testcase2:
        try:
            assert s.calc_suffix(s.midfix2suffix(testcase[0])) == testcase[1], ""
        except AssertionError:
            print("ERROR!")
            print("require:", testcase[1])
            print("return :", s.calc_suffix(s.midfix2suffix(testcase[0])))
    print("END successfully!")
