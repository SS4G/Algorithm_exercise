## 103. Binary Tree Zigzag Level Order Traversal

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],

```
。  3
   / \
  9  20
    /  \
   15   7
```

return its zigzag level order traversal as:

```
[
  [3],
  [20,9],
  [15,7]
]
```
#### tips
使用层次遍历
然后翻转 偶数行的结果即可

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
    def zigzagLevelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        if root is None:
            return []

        queue = [(root, 0)]
        ptr = 0
        maxLayer = 0
        while ptr < len(queue):
            if queue[ptr][0].left is not None:
                queue.append((queue[ptr][0].left, queue[ptr][1] + 1))
                maxLayer = queue[ptr][1] + 1
            if queue[ptr][0].right is not None:
                queue.append((queue[ptr][0].right, queue[ptr][1] + 1))
                maxLayer = queue[ptr][1] + 1
            ptr += 1
        dummy = []
        res = []
        for i in range(maxLayer+1):
            res.append(dummy[:])
        for node in queue:
            res[node[1]].append(node[0].val)
        for i in range(len(res)):
            if i & 0x01 != 0:
                res[i].reverse()
        return res
```
