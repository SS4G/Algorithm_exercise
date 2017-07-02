## 337. House Robber III

The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

```
。
     3
    / \
   2   3
    \   \ 
     3   1
```

Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:

```
。
     3
    / \
   4   5
  / \   \ 
 1   3   1
```

Maximum amount of money the thief can rob = 4 + 5 = 9.

#### tips
这次还是延续leet198的老套路 标记上一层次的 商店是抢还是不抢 上次是线性的动态规划 这次是 从树底部到上部进行动态规划 吧每次遍历一颗树的的抢或者不抢的最大值 通过递归链传递上去

#### mycode

```
class Solution(object):
    def rob(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        return max(self.robRecursive(root))

    def robRecursive(self, root):
        if root is None:
            return 0, 0  # rob, not rob
        leftYes, leftNo = self.robRecursive(root.left)
        rightYes, rightNo = self.robRecursive(root.right)
        rootYes = leftNo + rightNo + root.val
        rootNo = max(leftYes, leftNo) + max(rightNo, rightYes)
        return rootYes, rootNo
```
