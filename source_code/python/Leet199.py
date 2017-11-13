# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from AlgorithmTraining.G55Utils.Py.Utils import *

class Solution(object):
    def rightSideView(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
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
        result = [l[-1] for l in res]
        return result

if __name__ == "__main__":
    s = Solution()
    rootarr = [1, 2, 3, None, 5, None, 4]
    root = TreeUtil.deserialize(rootarr)
    print(s.rightSideView(root))







