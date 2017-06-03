# beat 60%
# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def levelOrderBottom(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        res = []
        fifo = []
        fifo.append((root.val, 0, root))
        index = 0
        while index < len(fifo):
            if fifo[index][2].left is not None:
                fifo.append((fifo[index][2].left.val, fifo[index][1]+1, fifo[index][2].left))
            if fifo[index][2].right is not None:
                fifo.append((fifo[index][2].right.val, fifo[index][1]+1, fifo[index][2].right))
            index += 1

        for node in fifo:
            if len(res) < node[1]+1:  # root is at level 0
                res.append([])
            res[node[1]].append(node[0])
        res.reverse()
        return res

n_3 = TreeNode(3)
n_9 = TreeNode(9)
n_20 = TreeNode(20)
n_15 = TreeNode(15)
n_7 = TreeNode(7)

n_3.left = n_9
n_3.right = n_20
n_20.left = n_15
n_20.right = n_7

s = Solution()
print(s.levelOrderBottom(n_3))
