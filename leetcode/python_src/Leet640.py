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