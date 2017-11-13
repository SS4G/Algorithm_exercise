## 230. Kth Smallest Element in a BST

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ? k ? BST's total elements.

Follow up:
- What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?


#### tips
使用中序遍历输出的第k个元素即是

#### mycode

```
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
```
