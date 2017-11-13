## 199. Binary Tree Right Side View

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,

```
.  1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
```

You should return [1, 3, 4].

#### tips
使用层次遍历 返回每层的最后的元素

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
    def rightSideView(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
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
        result = [l[-1] for l in res]
        return result
```
