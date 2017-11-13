## 663. Equal Tree Partition 
Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.

Example 1:
Input:  
```
、
。
    5
   / \
  10 10
    /  \
   2   3
```

Output: True
Explanation: 
```
。
    5
   / 
  10
```

Sum: 15

```
。
   10
  /  \
 2    3
```

Sum: 15
Example 2:
Input:     

```
。
    1
   / \
  2  10
    /  \
   2   20
```


Output: False
Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.

#### tips
要把一棵树分成和相等的连个部分 那么 必然有一颗包含根节点的子树和一颗不包含根节点的子树。所以先遍历一边 吧整颗树的和求出来 然后 在用递归去找一半和的子树的根节点


#### mycode
```
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

from AlgorithmTraining.G55Utils.Py.Utils import *


class Solution(object):
    def checkEqualTree(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        totalSum = self.traversRootSum(root)
        if totalSum & 0x01 == 0:
            return self.traverse2nd(root, totalSum >> 1)[1]
        else:
            return False

    def traversRootSum(self, root):
        if root is not None:
            leftSum = self.traversRootSum(root.leftSum)
            rightSum = self.traversRootSum(root.rightSum)
            return root.val + leftSum + rightSum
        else:
            return 0

    def traverse2nd(self, root, halfSum):
        if root is not None:
            leftSum, ls = self.traverse2nd(root.leftSum, halfSum)
            rightSum, rs = self.traverse2nd(root.rightSum, halfSum)
            if ls or rs:
                return 0, True
            elif root.val + leftSum + rightSum == halfSum:
                return 0, True
            else:
                return root.val + leftSum + rightSum, False
        else:
            return 0, False
```
