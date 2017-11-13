## 662. Maximum Width of Binary Tree
Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input: 

```
.
           1
         /   \
        3     2
       / \     \  
      5   3     9
```

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:
Input: 

```
.
          1
         /  
        3    
       / \       
      5   3
```

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:
Input: 

```
.
          1
         / \
        3   2 
       /        
      5
```

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:
Input: 

```
.
          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
```

Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer.

#### tips
使用满二叉树的坐标去标记每个节点 然后使用map记录每个层次上最外侧的两个点
使用preorder去做即可

但是要注意 在初始化每个层次的最外侧节点的记录时 不要使用Integer.max 或者Integer.min 这类数据 如果测试用例很特殊 将会超出这两个32bit的值的范围 所以最好使用None来代表初始化的值，这样会好一些。

虽然结果是32bit 但是中间 可能会超出范围 但是因为那个 纯右偏树测试用例的特殊性， 溢出也无所谓

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
    def widthOfBinaryTree(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root is None:
            return 0
        map = {}
        self.traverseHelper(map, root, 0, 0)
        maxidth = 0
        for interval in map.values():
            maxidth = max(maxidth, interval[1] - interval[0] + 1)
        return maxidth

    def traverseHelper(self, map, root, offset, level):
        levelFirstFlag = False
        if root is not None:
            if level not in map:
                map[level] = [None, None]  # - , +
                levelFirstFlag = True
            if map[level][1] is None or offset > map[level][1]:
                map[level][1] = offset
            if map[level][0] is None or offset < map[level][0]:
                map[level][0] = offset
            # newOffset = 0 if levelFirstFlag else offset
            newOffset = offset
            self.traverseHelper(map, root.left, newOffset * 2, level + 1)
            self.traverseHelper(map, root.right, newOffset * 2 + 1, level + 1)
```
