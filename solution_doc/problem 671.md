## 671. Second Minimum Node In a Binary Tree

Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input: 

```
.
    2
   / \
  2   5
     / \
    5   7
```


Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
Example 2:
Input: 

```
.
    2
   / \
  2   2
```

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.

#### tips
沿着最小值得路径查找 如果一个最小值节点的一个孩子不是最小值 那么这个孩子有可能成为第二小的节点 而且第二小不会再出现在这个节点的子树中 
还有种情况是 最小值的两个孩子节点都是最小值 这是需要递归的对这两个节点调用找第二小的函数 然后选取两者中最小的作为结果

#### mycode

```
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
```
