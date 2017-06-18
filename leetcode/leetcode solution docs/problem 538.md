## 538. Convert BST to Greater Tree

Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:

```
.             5
            /   \
           2     13
```

Output: The root of a Greater Tree like this:

```
.            18
            /   \
          20     13
```

#### tips
使用中序遍历 一遍遍历一边修改中间节点的值 并且用一个全局变量（或者外部的数组） 来记录下右边所有节点的值的和 这个中序遍历需要小小的修改一下 ， 需要从右子树开始 然后中间 然后才是左边

#### mycode

```
class Solution(object):
    def convertBST(self, root):
        """
        :type root: TreeNode
        :rtype: TreeNode
        """
        previousValue = [0, ]
        self.midTraverse(root, previousValue)
        return root

    def midTraverse(self, root, val):
        if root is None:
            return
        self.midTraverse(root.right, val)
        root.val += val[0]
        val[0] = root.val
        self.midTraverse(root.left, val)
        return
```
