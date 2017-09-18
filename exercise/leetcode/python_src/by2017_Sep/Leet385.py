# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
class NestedInteger(object):
    def __init__(self, value=None):
        """
        If value is not specified, initializes an empty list.
        Otherwise initializes a single integer equal to value.
        """

    def isInteger(self):
        """
        @return True if this NestedInteger holds a single integer, rather than a nested list.
        :rtype bool
        """

    def add(self, elem):
        """
        Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
        :rtype void
        """

    def setInteger(self, value):
        """
        Set this NestedInteger to hold a single integer equal to value.
        :rtype void
        """

    def getInteger(self):
        """
        @return the single integer that this NestedInteger holds, if it holds a single integer
        Return None if this NestedInteger holds a nested list
        :rtype int
        """

    def getList(self):
        """
        @return the nested list that this NestedInteger holds, if it holds a nested list
        Return None if this NestedInteger holds a single integer
        :rtype List[NestedInteger]
        """


class Solution(object):
    def deserialize(self, s):
        """
        :type s: str
        :rtype: NestedInteger
        """
        if s == "":
            return NestedInteger([])
        res = self.parser(s)
        return NestedInteger(res[0]) if len(res) == 1 else NestedInteger(res)

    def parser(self, str):
        i = 0
        digFlag = False
        rootResult = []
        tmpResult = rootResult
        stack = []
        while i < len(str):
            if (self.isDigit(str[i])) and digFlag is False:
                digFlag = True
                st = i
            elif not self.isDigit(str[i]) and digFlag is True:
                end = i
                val = int(str[st: end])
                tmpResult.append(val)
                digFlag = False
                if str[i] == ']':
                    tmpResult = stack.pop()
            elif str[i] == '[':
                tmpResult.append([])
                stack.append(tmpResult)
                tmpResult = tmpResult[-1]
            elif str[i] == ']':
                tmpResult = stack.pop()

            i += 1
        if digFlag is True:  # special situation “324”
            val = int(str[st: ])
            tmpResult.append(val)

        return rootResult

    def isDigit(self, c):
        return ord('0') <= ord(c) <= ord('9') or c == '-'

if __name__ == "__main__":
    s = Solution()
    str0 = "324"
    str1 = "[324, 567]"
    str2 = "[324, 567, [456, 789]"
    str3 = "[324, [567]]"
    print(s.deserialize(str3))

