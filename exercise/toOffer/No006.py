# -*- coding:utf-8 -*-
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from AlgorithmTraining.G55Utils.Py.Utils import *
class Solution:
    # 返回构造的TreeNode根节点
    def reConstructBinaryTree(self, pre, tin):
        # write code here
        return self.reConstructRecursive(pre, tin)

    def reConstructRecursive(self, pre, tin):
        if len(pre) == 0 or len(tin) == 0:
            return None
        rootNode = pre[0]
        spIdx = tin.index(rootNode)
        root = TreeNode(rootNode)
        if len(pre) > 1:
            root.left = self.reConstructRecursive(pre[1:], tin[: spIdx])
        if len(pre) > 1 + spIdx:
            root.right = self.reConstructRecursive(pre[1 + spIdx: ], tin[spIdx + 1: ])
        return root

if __name__ == "__main__":
    s = Solution()
    pre = [1, 2, 4, 7, 3, 5, 6, 8]
    tin = [4, 7, 2, 1, 5, 3, 8, 6]
    pre = []
    tin = []
    root = s.reConstructBinaryTree(pre, tin)
    TreeUtil.showTree(root)