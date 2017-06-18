from AlgorithmTraining.G55Utils.Py.Utils import *


class Solution(object):
    def pathSum(self, root, sum):
        """
        :type root: TreeNode
        :type sum: int
        :rtype: List[List[int]]
        """
        res = []
        pathStack = []
        lastSum = [0, ]
        if root is None:
            return []
        self.pathSumRecusive(root, lastSum, pathStack, sum, res)
        return res

    def pathSumRecusive(self, root, lastSum, pathStack, sum, result):
        if root is None:
            return
        else:
            lastSum[0] += root.val
            pathStack.append(root)
            if root.left is not None:
                self.pathSumRecusive(root.left, lastSum, pathStack, sum, result)
            if root.right is not None:
                self.pathSumRecusive(root.right, lastSum, pathStack, sum, result)
            if root.left is None and root .right is None:
                if lastSum[0] == sum:
                    result.append([n.val for n in pathStack])
            pathStack.pop()
            lastSum[0] -= root.val

if __name__ == "__main__":
    s = Solution()
    arr = [5, 4, 8, 11, None, 13, 4, 7, 2, None, None, 5, 1]
    treeRoot = TreeUtil.deserialize(arr)
    res = s.pathSum(treeRoot, 22)
    print(res)