from AlgorithmTraining.G55Utils.Py.Utils import *
class Solution:
    def findSecondMinimumValue(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root is None:
            return -1
        elif root.left is None:
            return -1
        elif root.left.val == root.right.val:
            return self.handleEqula(root)
        else:
            minPtr = root
            maybeSecondPtr = root.left if root.left.val != root.val else root.right
            while minPtr.left is not None: # has sub node
                minPtr = minPtr.left if minPtr.left.val == minPtr.val else minPtr.right
                if minPtr.left is not None:
                    if minPtr.left.val == minPtr.right.val:
                        equal2ndMin = self.handleEqula(minPtr)
                        return min(self.handleEqula(minPtr), maybeSecondPtr.val) if equal2ndMin != -1 else maybeSecondPtr.val
                    else:
                        anotherDiffPtr = minPtr.left if minPtr.left.val != root.val else minPtr.right
                        if anotherDiffPtr.val < maybeSecondPtr.val:
                            maybeSecondPtr = anotherDiffPtr
            return maybeSecondPtr.val

    def handleEqula(self, root):
        assert root.left.val == root.right.val
        leftSecondVal = self.findSecondMinimumValue(root.left)
        rightSecondVal = self.findSecondMinimumValue(root.right)
        if leftSecondVal == -1 and rightSecondVal == -1:
            return -1
        elif leftSecondVal == -1 or rightSecondVal == -1:
            return leftSecondVal if rightSecondVal == -1 else rightSecondVal
        else:
            return min(leftSecondVal, rightSecondVal)

if __name__ == "__main__":
    s = Solution()
    arr = [2, 2, 7, 2, 3, None, None, 2, 2, 3, 5, 2, 2, 2, 2]
    #arr = [2, 2, 3, 2, 2, 3, 5, 2, 2, 2, 2]
    #arr = [1, 1, 2, 1, 3]
    #arr = [1, 1, 1, 1, 5, 1, 3]
    #arr = [1, 1, 1, 1, 1, 1, 1]
    root = TreeUtil.deserialize(arr)
    TreeUtil.showTree(root)
    print(s.findSecondMinimumValue(root))


