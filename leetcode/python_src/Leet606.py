# Definition for a binary tree node.

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def tree2str(self, t):
        """
        :type t: TreeNode
        :rtype: str
        """
        output = []
        self.traverse(t, output)
        return "".join(output)

    def traverse(self, root, output):
        if root is None:
            return
        else:
            output.append(str(root.val))
            if root.left is not None or root.right is not None:
                output.append("(")
                self.traverse(root.left, output)
                output.append(")")
                if root.right is not None:
                    output.append("(")
                    self.traverse(root.right, output)
                    output.append(")")

if __name__ == "__main__":
     a1 = TreeNode(1)
     a2 = TreeNode(2)
     a3 = TreeNode(3)
     a4 = TreeNode(4)
     a1.left = a2
     a2.left = a3
     a3.left = a4
     s = Solution()
     print(s.tree2str(a1))






