# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def largestValues(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root is None:
            return []

        nodes = [(root, 0), ]
        ptr = 0
        while ptr < len(nodes):
            row = nodes[ptr][1]
            if nodes[ptr][0].left is not None:
                nodes.append((nodes[ptr][0].left, row + 1))
            if nodes[ptr][0].right is not None:
                nodes.append((nodes[ptr][0].right, row + 1))
            ptr += 1
        print([n[0].val for n in nodes])
        res = []
        lastRow = -1
        for i in nodes:
            if lastRow != i[1]:
                if lastRow != -1:
                    res.append(maxThisRow)
                maxThisRow = i[0].val
                lastRow = i[1]
            else:
                maxThisRow = max(maxThisRow, i[0].val)
        res.append(maxThisRow)
        return res

if __name__ == "__main__":
    s = Solution()
    a0 = TreeNode(3)
    a1 = TreeNode(4)
    a2 = TreeNode(5)
    a3 = TreeNode(6)
    a4 = TreeNode(3)
    a5 = TreeNode(1)
    a6 = TreeNode(2)

    a0.left = a1
    a0.right = a2
    a1.left = a3
    a1.right = a4
    a2.left = a5
    a2.right = a6

    print(s.largestValues(a0))

