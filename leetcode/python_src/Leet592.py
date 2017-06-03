import re
class Solution(object):
    def fractionAddition(self, expression):
        """
        :type expression: str
        :rtype: str
        """
        return self.parserExpression(expression)

    def getMaxCommondivNum(self, a, b):
        """
        传说中的辗转相除法
        m n 迭代的结果符合两条规则
        r=m%n
        1.r 不小于mn的最大公约数
        2.r 肯定小于 max(m,n) 的1/2 一直在变小
        所以两个条件的焦点 就是最大公约数 至于是怎样收敛到 最大公约数的可以不必关心
        :return:   max common divisor of m,n
        law:
            if M>=N
            then M%N<M/2
        """
        if a < b:
            a, b = b, a
        while b != 0:
           a, b = b, a % b
        return a

    def parserExpression(self, exp):
        negMark = []
        if self.isNegParam(exp[0]):
            negMark.append(self.isNegParam(exp[0]))
        else:
            negMark.append(1)

        for i in range(1, len(exp)):
            mark = self.isNegParam(exp[i])
            if mark:
                negMark.append(mark)
        print(negMark)
        fract = [f for f in re.split("[\\+|-]", exp) if f != ""]
        fractions = []
        i = 0
        for f in fract:
            a = f.split("/")
            son = int(a[0])
            mather = int(a[1])
            fractions.append([son, mather, negMark[i]])
            i += 1


        maxMather = 1
        for f in fractions:
            maxMather *= f[1]  # 最大的公分母

        sumOfSon = 0
        for f in fractions:
            tmpSon = f[0]*(maxMather//f[1])
            if f[2] == 2:
                tmpSon = -tmpSon
            sumOfSon += tmpSon
        if sumOfSon == 0:
            return "0/1"

        print("bug:"+str(sumOfSon)+"/"+str(maxMather))

        commonDiv = self.getMaxCommondivNum(abs(sumOfSon), maxMather)
        self.getMaxCommondivNum(55, 84)
        print(commonDiv)
        maxMather //= commonDiv
        sumOfSon //= commonDiv
        return str(sumOfSon)+"/"+str(maxMather)


    def isNegParam(self, s):
        if s == "+":
            return 1
        elif s == "-":
            return 2
        else:
            return 0

if __name__ == "__main__":
    s = Solution()
    #print(s.fractionAddition("-1/2+1/2"))
    #print(s.fractionAddition("2/3+7/6+1/5-2/3+5/2+3/7+9/4+5/9-5/6+1/10"))
    print(s.fractionAddition("-4/7-3/4+2/3"))