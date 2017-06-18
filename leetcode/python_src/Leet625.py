class Solution(object):
    def smallestFactorization(self, a):
        """
        :type a: int
        :rtype: int
        """
        if a == 1:
            return 1
        elif a == 0:
            return 0

        listdiv = (9, 8, 7, 6, 5, 4, 3, 2)
        stack = []
        output = []
        minLen = [12]
        self.splitWithIn10(a, stack, 0, minLen, listdiv, output, 9)
        if len(output) == 0:
            return 0

        for i in range(len(output)):
            output[i].sort()

        strRes = ["".join([str(d) for d in o]) for o in output]
        intRes = min([int(s) for s in strRes])
        if intRes > 0x7fffffff:
            return 0
        else:
            return intRes


    def splitWithIn10(self, num, stack, currentLength, minLegthRes, listDiv, output, limit):
        if currentLength > minLegthRes[0]:
            return
        for div in listDiv:
            if div <= limit:
                if num % div == 0:
                    stack.append(div)
                    if num//div == 1:
                        if len(stack) <= minLegthRes[0]:
                            output.append(stack[:])
                            minLegthRes[0] = len(stack)
                    else:
                        self.splitWithIn10(num//div, stack, currentLength + 1, minLegthRes, listDiv, output, div)
                    stack.pop()
            else:
                pass
        return

if __name__ == "__main__":
    s = Solution()
    print(s.smallestFactorization(1))



