## 166. Fraction to Recurring Decimal
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".

#### tips
这个求循环小数， 循环小数的条件本质上是一种状态的循环 具体是哪一种状态 需要我们来定义，因为除数是一定的 所以被除数可以作为状态的指示 在除的过程中 被除数可以作为状态 只要某个被除数在之前出现过， 那么就说明 又会重复刚才的情况
所以我们 可以用一个set来保存出现过的除数 只要出现了除数 我们就把原来出现过这个除数时及之后到这个第二次除数出现前的结果写成循环部分即可。

最后有一个要注意的问题是 最终的结果可能会有点问题 如果以被除数作为状态的话

比如最终的结果为 "1.3456(56)" 其实真正的结果应该是1.34(56)
所以应该用字符串处理函数来处理一下结果 吧多出来的部分 卷回去即可

#### mycode


```
class Solution(object):
    def fractionToDecimal(self, numerator, denominator):
        """
        :type numerator: int
        :type denominator: int
        :rtype: str
        """
        negFlag = True if numerator * denominator < 0 else False
        numerator = abs(numerator)
        denominator = abs(denominator)

        pastState = set([])
        pastStateRecord = []
        intPart = numerator // denominator
        numerator = numerator % denominator
        if numerator == 0:
            res = str(intPart)
            return "-" + res if negFlag else res
        cycleFlag = True
        while numerator not in pastState:
            #print(numerator)
            thisTurnRes = []
            tmpNumrator = numerator
            j = 0
            while tmpNumrator < denominator:
                j += 1
                tmpNumrator *= 10
            thisTurnRes.append('0' * (j - 1))

            thisTurnRes.append(str(tmpNumrator // denominator))  # add result
            reminder = tmpNumrator % denominator
            pastState.add(numerator)
            #pastState.add(reminder)
            #pastStateRecord.append((reminder, str(tmpNumrator // denominator)))
            pastStateRecord.append((numerator, "".join(thisTurnRes)))
            nextNumrator = tmpNumrator % denominator
            if nextNumrator == 0:
                cycleFlag = False
                break
            numerator = nextNumrator

        pointPart = []
        if cycleFlag:
            for n in pastStateRecord:
                if n[0] == numerator:
                    pointPart.append("(")
                    pointPart.append(n[1])

                else:
                    pointPart.append(n[1])
            pointPart.append(")")
            res = str(intPart) + "." + "".join(pointPart)
            res = self.backMathch(res)
        else:
            for n in pastStateRecord:
                pointPart.append(n[1])
            res = str(intPart) + "." + "".join(pointPart)

        return "-" + res if negFlag else res

    def backMathch(self, pat):
        index0 = pat.find("(")
        i = len(pat) - 2
        j = index0 - 1
        flag = False
        while pat[i] == pat[j]:
            flag = True
            i -= 1
            j -= 1
        if flag:
            i += 1
            j += 1
            return "".join([pat[: j], "(", pat[j: index0], pat[index0 + 1: i], ")"])
        else:
            return pat
```

```

```
