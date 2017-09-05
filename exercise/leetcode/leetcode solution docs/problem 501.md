## 501. Find Mode in Binary Search Tree Add to List

Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.

```
For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].
```

Note: If a tree has more than one mode, you can return them in any order.

**Follow up:** Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
#### tips
使用中序遍历，因为bst的中序遍历是一个排序完成的序列 这样就可以一边数一边确定结果，复杂度只是O(n)且只需要遍历一次
程序中使用一个计数当前值的记录 来实现mode的查找
对于可能存在的多个mode 使用了python list的 append方法来实现
#### mycode
```Python
# beats 60%
# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def __init__(self):
        self.this_mode = None
        self.this_cnt = 0
        self.res_list = [0, []]  # [max val, max_num_list]

    def findMode(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        self.this_mode = -100001001001
        self.this_cnt = -1
        self.res_list = [-1, []]  # [max amount, max_num_list]

        if root is None:
            return []
        self.traverse(root)
        if self.this_cnt > self.res_list[0]:  # refresh the reslist
            self.res_list[0] = self.this_cnt
            self.res_list[1] = [self.this_mode, ]
        elif self.this_cnt == self.res_list[0]:
            self.res_list[1].append(self.this_mode)
        return self.res_list[1]


    def traverse(self, root):
        if root is not None:
            self.traverse(root.left)
            if root.val != self.this_mode:
                self.old_mode = self.this_mode  # save old
                self.old_cnt = self.this_cnt

                self.this_cnt = 1
                self.this_mode = root.val
                if self.old_cnt > self.res_list[0]:  # refresh the reslist
                    self.res_list[0] = self.old_cnt
                    self.res_list[1] = [self.old_mode,]
                elif self.old_cnt == self.res_list[0]:
                    self.res_list[1].append(self.old_mode)
            else:
                self.this_cnt += 1

            self.traverse(root.right)

if __name__ == "__main__":
    a = TreeNode(1)
    b = TreeNode(1)
    c = TreeNode(1)
    d = TreeNode(2)
    a.left = b
    a.right = c
    c.right = d
    s = Solution()
    print(s.findMode(a))
```
