import operator
from AlgorithmTraining.G55Utils.Py.Utils import *


class Solution(object):
    def findFrequentTreeSum(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root is None:
            return []

        outputDict = {}
        self.getSumRecursive(root, outputDict)
        itesm0 = list(outputDict.items())
        itesm0.sort(key=operator.itemgetter(1), reverse=True)
        maxFreq = itesm0[0][1]
        res = []
        for i in range(len(itesm0)):
            if itesm0[i][1] == maxFreq:
                res.append(itesm0[i][0])  # add key
            else:
                break
        return res

    def getSumRecursive(self, root, outputDict):
        if root is None:
            return 0
        else:
            leftSum = self.getSumRecursive(root.left, outputDict)
            rightSum = self.getSumRecursive(root.right, outputDict)
            thisSum = leftSum + rightSum + root.val
            outputDict.setdefault(thisSum, 0)
            outputDict[thisSum] += 1
            return thisSum

if __name__ == "__main__":
    s = Solution()
    arr = [5, -5]
    root = TreeUtil.deserialize(arr)
    print(s.findFrequentTreeSum(root))