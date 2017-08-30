# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from AlgorithmTraining.G55Utils.Py.Utils import *


class Solution(object):
    def widthOfBinaryTree(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root is None:
            return 0
        map = {}
        self.traverseHelper(map, root, 0, 0)
        maxidth = 0
        for interval in map.values():
            maxidth = max(maxidth, interval[1] - interval[0] + 1)
        return maxidth

    def traverseHelper(self, map, root, offset, level):
        levelFirstFlag = False
        if root is not None:
            if level not in map:
                map[level] = [None, None]  # - , +
                levelFirstFlag = True
            if map[level][1] is None or offset > map[level][1]:
                map[level][1] = offset
            if map[level][0] is None or offset < map[level][0]:
                map[level][0] = offset
            # newOffset = 0 if levelFirstFlag else offset
            newOffset = offset
            self.traverseHelper(map, root.left, newOffset * 2, level + 1)
            self.traverseHelper(map, root.right, newOffset * 2 + 1, level + 1)


if __name__ == "__main__":
    arr = [1, 3, 2, 5, 3, None, 9]
    arr = [1, 3, None, 9, 4]
    arr = [1, 3, 9, 4]
    arr = [1, 3, 2, 5, None, None, 9, 6, None, None, 7]
    arr = [1,1,1,1,1,1,1,None,None,None,1,None,None,None,None,2,2,2,2,2,2,2,None,2,None,None,2,None,2]
    arr = [0,None,0,None,0,None,0,None,0,None,0,None,0,None,0,None,0,None,0,None,0]
    root = TreeUtil.deserialize(arr)
    s = Solution()
    print(s.widthOfBinaryTree(root))