# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def sumNumbers(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        stack = []
        output = []
        if root is None:
            return 0
        self.preOrderTraverse(root, stack, output)
        if len(output) == 0:
            return 0
        return sum([int(i) for i in output])

    def preOrderTraverse(self, root, stack, output):
        stack.append(str(root.val))
        if root.left is None and root.right is None:
            output.append("".join(stack))
            stack.pop()
            return
        if root.left is not None:
            self.preOrderTraverse(root.left, stack, output)
        if root.right is not None:
            self.preOrderTraverse(root.right, stack, output)
        stack.pop()

if __name__ == "__main__":
    s = Solution()
    a0 = TreeNode(1)
    a1 = TreeNode(2)
    a2 = TreeNode(3)
    a0.left = a1
    a0.right = a2
    print(s.sumNumbers(a0))

