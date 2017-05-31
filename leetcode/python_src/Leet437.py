# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def __init__(self):
        self.stack = []

    def pathSum(self, root, sum):
        """
        :type root: TreeNode
        :type sum: int
        :rtype: int
        """
        presums = []
        return self.traverse(root, presums, sum)

    def traverse(self, root, presums, target):
        cnt = 0
        if root is None:
            return 0
        else:
            self.stack.append(root.val)
            newAdd = root.val
            self.getTarget(presums, newAdd)
            for i in range(len(presums)):
                if presums[i] == target:
                    cnt += 1

            cnt += self.traverse(root.left, presums, target)
            cnt += self.traverse(root.right, presums, target)
            # print("sum", presums)
            self.goBack(presums, root.val)
            # print("stack", self.stack, "\n")
            self.stack.pop()
            return cnt

    def getTarget(self, preSums, newAdd):
        for i in range(len(preSums)):
            preSums[i] += newAdd
        preSums.append(newAdd)

    def goBack(self, preSums, newAdd):
        for i in range(len(preSums)):
            preSums[i] -= newAdd
        preSums.pop()

if __name__ == "__main__":
    s = Solution()
    a0 = TreeNode(10)
    a1 = TreeNode(5)
    a2 = TreeNode(-3)
    a3 = TreeNode(3)
    a4 = TreeNode(2)
    a5 = TreeNode(11)
    a6 = TreeNode(3)
    a7 = TreeNode(-2)
    a8 = TreeNode(1)

    a0.left = a1
    a0.right = a2
    a1.left = a3
    a1.right = a4
    a2.right = a5
    a3.left = a6
    a3.right = a7
    a4.right = a8

    print(s.pathSum(a0, 8))
