class Solution(object):
    def isAdditiveNumber(self, num):
        """
        :type num: str
        :rtype: bool
        """
        if num == "":
            return False
        return self.validRecursive(0, num, -1, matching=False)

    def validRecursive(self, startIndex, num, lastVal, matching=True):
        for endIndex in range(startIndex + 1, len(num) + 1):
            thisVal = int(num[startIndex:endIndex])
            if thisVal < 10 and endIndex - startIndex >= 2:
                return False

            if not matching:
                nextState = self.validRecursive(endIndex, num, thisVal)
                if nextState:
                    return True
            else:
                nextVal = lastVal + thisVal
                nextStr = str(nextVal)
                if nextStr == num[endIndex:endIndex+len(nextStr)]:  # match success
                    if endIndex < len(num) - len(nextStr):
                        nextState = self.validRecursive(endIndex, num, thisVal)
                        if nextState:
                            return True
                    else:
                        return True
                else:
                    continue
        return False

if __name__ == "__main__":
    s = Solution()
    print(s.isAdditiveNumber("19"))