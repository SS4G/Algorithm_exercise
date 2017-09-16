# Definition for a binary tree node.
from AlgorithmTraining.G55Utils.Py.Utils import *

class Solution(object):
    def kthSmallest(self, root, k):
        """
        :type root: TreeNode
        :type k: int
        :rtype: int
        """
        if root is None:
            return 0
        output = []
        cntContainer = [0, ]
        self.inOrderTrverse(root, output, cntContainer, k)
        return output.pop()

    def inOrderTrverse(self, root, output, cntContainer, k):
        if root is None:
            return
        else:
            self.inOrderTrverse(root.left, output, cntContainer, k)
            if cntContainer[0] < k:
                cntContainer[0] += 1
                if cntContainer[0] == k:
                    output.append(root.val)
            else:
                return
            self.inOrderTrverse(root.right, output, cntContainer, k)

if __name__ == "__main__":
    s = Solution()
    arr = [4, 2, 6, 1, 3, 5, 7]
    root = TreeUtil.deserialize(arr)
    print(s.kthSmallest(root, 4))



