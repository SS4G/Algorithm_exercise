## 513. Find Bottom Left Tree Value Add to List

Given a binary tree, find the leftmost value in the last row of the tree.


```
Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2: 
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
```

Note: You may assume the tree (i.e., the given root node) is not NULL.
#### tips
使用先序遍历 同一行中 最左边的元素是这排中第一个被访问到的
#### mycode
```Python
class Solution(object):
    def __init__(self):
        self.most_deep_row = -1
        self.bottom_left_val = None
    
    def clear_status(self):
        self.most_deep_row = -1
        self.bottom_left_val = None
    
    def findBottomLeftValue(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        self.clear_status()
        self.pre_order_traverse(root, 0)  # start depth=0
        return self.bottom_left_val
        
    def pre_order_traverse(self, root, current_depth):
        if root is None:
            return 
        else:
            if current_depth > self.most_deep_row:  # current row 
                # update the msg
                self.most_deep_row = current_depth
                self.bottom_left_val = root.val
            self.pre_order_traverse(root.left, current_depth+1)
            self.pre_order_traverse(root.right, current_depth+1)
```
