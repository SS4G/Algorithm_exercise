# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from AlgorithmTraining.G55Utils.Py.Utils import *


class Solution(object):
    def generateTrees(self, n):
        """
        :type n: int
        :rtype: List[TreeNode]
        """
        if n == 0:
            return [None, ]

        record = [None, ] * (n + 1)
        record[0] = [None, ]
        record[1] = [TreeNode(1), ]
        for i in range(2, n + 1):
            record[i] = [] 
            for j in range(i):
                leftTrees = record[j]
                rightTrees = record[i - 1 - j]
                for l in leftTrees:
                    for r in rightTrees:
                        root = TreeNode(0)
                        root.left = self.copyTree(l)
                        root.right = self.copyTree(r)
                        record[i].append(root)
        for root in record[n]:
            self.renderTree(root)            
        return record[n]
        
    def copyTree(self, root):
        return self.copyTreeRecursive(root)
        
    def copyTreeRecursive(self, root):
        if root is None:
            return None
        else:
            rootCopy = TreeNode(root.val)
            rootCopy.left = self.copyTreeRecursive(root.left)
            rootCopy.right = self.copyTreeRecursive(root.right)
            return rootCopy
        
    def renderTree(self, root):
        lastMark = [0, ]
        self.renderRecursive(root, lastMark)
        
    def renderRecursive(self, root, lastMark):
        if root is None:
            return 
        self.renderRecursive(root.left, lastMark)
        root.val = lastMark[0] + 1
        lastMark[0] += 1
        self.renderRecursive(root.right, lastMark)

if __name__ == "__main__":
    s = Solution()
    n = 6
    trees = s.generateTrees(n)
    cnt = 0
    for i in trees:
        TreeUtil.showTree(i)
        cnt += 1
        print("%%%%%%%%%%%%%%", cnt)
