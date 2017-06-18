## 530. Minimum Absolute Difference in BST
Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.


```
Example:

Input:

   1
    \
     3
    /
   2

Output:
1
```


Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
Note: There are at least two nodes in this BST.
#### tips
- 二叉排序树的中序遍历是一个排了序的序列  
先进行中序遍历的到一个排序的序列 然后在相邻的两个节点中找最小的间距

#### mycode
```Python
class Solution(object):
    def __init__(self):
        self.mid_travers_res = []

    def getMinimumDifference(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        self.mid_order_travers(root)
        length = len(self.mid_travers_res)
        min = 1<<32
        for index in range(1, length):
            abs_val = abs(self.mid_travers_res[index] - self.mid_travers_res[index-1])
            min = abs_val if abs_val < min else min
        return min 
    
    def mid_order_travers(self, root):
        if root is None :
            return
        self.mid_order_travers(root.left)
        self.mid_travers_res.append(root.val)
        self.mid_order_travers(root.right)
```
