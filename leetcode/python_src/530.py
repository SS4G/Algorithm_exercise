# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def __init__(self):
        self.mid_travers_res = []

    def getMinimumDifference(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        self.mid_order_travers(root)
        length = len(self.mid_order_travers_res)
        min = 1<<32
        for index in range(1, length):
            abs_val = abs(self.mid_travers_res[index] - self.mid_travers_res[index-1])
            min = abs_val if abs_val < min else min
        return min

    def mid_order_travers(self, root):
        if root is None :
            return
        self.mid_order_travers(root.left)
        self.mid_travers_res.append(root.val)
        self.mid_order_travers(root.right)
