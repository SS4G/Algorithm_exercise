# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from AlgorithmTraining.G55Utils.Py.Utils import *

class Solution(object):
    def zigzagLevelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        if root is None:
            return []

        queue = [(root, 0)]
        ptr = 0
        maxLayer = 0
        while ptr < len(queue):
            if queue[ptr][0].left is not None:
                queue.append((queue[ptr][0].left, queue[ptr][1] + 1))
                maxLayer = queue[ptr][1] + 1
            if queue[ptr][0].right is not None:
                queue.append((queue[ptr][0].right, queue[ptr][1] + 1))
                maxLayer = queue[ptr][1] + 1
            ptr += 1
        dummy = []
        res = []
        for i in range(maxLayer+1):
            res.append(dummy[:])
        for node in queue:
            res[node[1]].append(node[0].val)
        for i in range(len(res)):
            if i & 0x01 != 0:
                res[i].reverse()
        return res

if __name__ == "__main__":
    s = Solution()
    rootarr = [3,9,20,None,None,15,7]
    root = TreeUtil.deserialize(rootarr)
    print(s.zigzagLevelOrder(root))







