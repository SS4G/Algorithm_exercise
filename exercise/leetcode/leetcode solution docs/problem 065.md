## 65. Valid Number Add to List
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
#### tips
可以使用一个类似于决策树的过程来分析介符（. e .e） 两边的成分
因为不同情况下两边成分对应的 组合不同所以要使用一个判断数字类型的函数isInteger() 不仅仅要给出真假
更要给出具体的类型
具体的testcase是重点 见代码下方
#### mycode
```Python
class Solution(object):
    def isNumber(self, s):
        """
        :type s: str
        :rtype: bool
        """
        s =s.strip()
        if len(s) < 1:
            return False
        elif len(s) == 1:
            return self.isDigit(s)

        if ".e" in s:
            MIDDLE_MARK = ".e"
        elif "." in s:
            MIDDLE_MARK = "."
        elif "e" in s:
            MIDDLE_MARK = "e"
        else:
            MIDDLE_MARK = None

        if MIDDLE_MARK is not None:
            list0 = s.split(MIDDLE_MARK)
            if len(list0) != 2:
                return False
            leftStatus = self.isInteger(list0[0])
            rightStatus = self.isInteger(list0[1])
            if MIDDLE_MARK == ".":
                if (leftStatus == 0 or leftStatus == 4) and rightStatus == 0:
                    return False
                else:
                    return (leftStatus in [0, 1, 2, 3, 4]) and (rightStatus in [0, 1, 5])
            if MIDDLE_MARK == "e":
                return (leftStatus in [1, 2, 3]) and (rightStatus in [1, 2, 3])
            if MIDDLE_MARK == ".e":
                return (leftStatus in [1, 2, 3]) and (rightStatus in [1, 2, 3])

        else:
            return self.isInteger(s) > 0

    def isInteger(self, s):
        """

        :param s:
        :return: -1->invalid 0->"" 1->Integer 2-> -Integer 3->+Integer 4-> +/- only
        """
        if s == "":
            return 0
        elif "e" in s: #eg 4e23
            lista = s.split("e")
            lstatus = self.isInteger(lista[0])
            rstatus = self.isInteger(lista[1])
            if lstatus in [1, ] and rstatus in [1, 2, 3]:
                return 5
            else:
                return -1
        elif s[0] == "+" or s[0] == "-":
            st = 1
        else:
            st = 0
        if len(s) <= 1 and st == 1:
            return 4

        while st < len(s):
            if self.isDigit(s[st]):
                st += 1
            else:
                return -1

        if s[0] == "+":
            return 3
        elif s[0] == "-":
            return 2
        else:
            return 1

    def isDigit(self, s):
        return s in "0123456789"




if __name__ == "__main__":
    s = Solution()

    #Testcase
    print(s.isNumber("4e+"), False)
    print(s.isNumber(".-4"), False)
    print(s.isNumber("123e567"), True)
    print(s.isNumber("-123e567"), True)
    print(s.isNumber("123.567"), True)
    print(s.isNumber(".1"), True)
    print(s.isNumber("3."), True)
    print(s.isNumber("."), False)
    print(s.isNumber("+.8"), True)
    print(s.isNumber(".+8"), False)
    print(s.isNumber("e9"), False)
    print(s.isNumber("46.e3"), True)
    print(s.isNumber("46.2e3"), True)
    print(s.isNumber("-."), False)
```
