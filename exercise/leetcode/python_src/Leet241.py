import re


class Solution(object):
    def diffWaysToCompute(self, input):
        """
        :type input: str
        :rtype: List[int]
        """
        nums = re.split(string=input, pattern="[\+\-\*]")
        nums = [int(i) for i in nums]
        operators = re.split(string=input, pattern="\\d+")
        operators = [i for i in operators if len(i) > 0]

        types = self.assignPriority(operators)
        res = []
        for type in types:
            res.append(self.calculate(nums, operators, type))
        return res

    def calculate(self, nums, operators, priority):
        if len(operators) == 0 and len(nums) == 1:
            return nums[0]
        elif len(operators) == 1:
            op = operators[0]
            leftRes = nums[0]
            rightRes = nums[1]
        else:
            idx, val = self.getMaxIndex(priority)
            op = operators[idx]
            leftRes = self.calculate(nums[:idx + 1], operators[:idx], priority[:idx])
            rightRes = self.calculate(nums[idx + 1:], operators[idx + 1:], priority[idx + 1:])
        if op == "+":
            return leftRes + rightRes
        elif op == "-":
            return leftRes - rightRes
        elif op == "*":
            return leftRes * rightRes
        else:
            assert False, "wtf?"
            return 0

    def getMaxIndex(self, li):
        maxval = -0xfffffff
        maxIndex = -1
        for i in range(len(li)):
            if maxval <= li[i]:
                maxval = li[i]
                maxIndex = i
        return maxIndex, maxval

    def assignPriority(self, operators):
        dprec = {}
        return self.assignPriorityRecursive(len(operators), dprec)

    def assignPriorityRecursive(self, opsLen, recDict):
        if opsLen == 0:
            return [[]]
        if opsLen in recDict:
            return recDict[opsLen]
        res = []
        for i in range(opsLen):
            left = self.assignPriorityRecursive(i, recDict)
            right = self.assignPriorityRecursive(opsLen - (i + 1), recDict)
            for l in left:
                for r in right:
                    tmpRes = l[:] + [opsLen, ] + r[:]
                    res.append(tmpRes)
        recDict[opsLen] = res
        return res

if __name__ == "__main__":
    s = Solution()
    dit = {}
    print(s.diffWaysToCompute("2*3-4*5"))

