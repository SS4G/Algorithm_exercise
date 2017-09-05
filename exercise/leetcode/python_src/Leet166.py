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

if __name__ == "__main__":
    s = Solution()
    #print(s.fractionToDecimal(7, -12))
    #print(s.fractionToDecimal(1, 30))
    print(s.fractionToDecimal(-2147483648, 1))

