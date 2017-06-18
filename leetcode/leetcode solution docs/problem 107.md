## 107. Binary Tree Level Order Traversal II 

Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).


```
For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
```

#### tips
使用fifo进行层次遍历 然后将结果翻转即可

#### mycode


```Python
# beat 60%
# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def levelOrderBottom(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        res = []
        fifo = []
        fifo.append((root.val, 0, root))
        index = 0
        while index < len(fifo):
            if fifo[index][2].left is not None:
                fifo.append((fifo[index][2].left.val, fifo[index][1]+1, fifo[index][2].left))
            if fifo[index][2].right is not None:
                fifo.append((fifo[index][2].right.val, fifo[index][1]+1, fifo[index][2].right))
            index += 1

        for node in fifo:
            if len(res) < node[1]+1:  # root is at level 0
                res.append([])
            res[node[1]].append(node[0])
        res.reverse()
        return res
```
