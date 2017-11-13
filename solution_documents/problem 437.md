## 437. Path Sum III

You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8


```
......10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1
```


Return 3. The paths that sum to 8 are:


```
1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
```

#### tips
使用先序遍历 同时使用堆栈记录下 当前节点到根节点的路径 每次遍历一个节点时 检查路径堆栈中是否有 target 
查找target 要使用一个数组记录各种起点的到当前顶点的和 遇到target 时 将结果加一
每返回一层后 就从堆栈中pop出相应的信息

#### mycode

```
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def __init__(self):
        self.stack = []

    def pathSum(self, root, sum):
        """
        :type root: TreeNode
        :type sum: int
        :rtype: int
        """
        presums = []
        return self.traverse(root, presums, sum)

    def traverse(self, root, presums, target):
        cnt = 0
        if root is None:
            return 0
        else:
            self.stack.append(root.val)
            newAdd = root.val
            self.getTarget(presums, newAdd)
            for i in range(len(presums)):
                if presums[i] == target:
                    cnt += 1

            cnt += self.traverse(root.left, presums, target)
            cnt += self.traverse(root.right, presums, target)
            # print("sum", presums)
            self.goBack(presums, root.val)
            # print("stack", self.stack, "\n")
            self.stack.pop()
            return cnt

    def getTarget(self, preSums, newAdd):
        for i in range(len(preSums)):
            preSums[i] += newAdd
        preSums.append(newAdd)

    def goBack(self, preSums, newAdd):
        for i in range(len(preSums)):
            preSums[i] -= newAdd
        preSums.pop()
```

#### other code
Typical recursive DFS.
Space: O(n) due to recursion.
Time: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree).

这个方法的递归也不错 重点在pathSum函数的构造中 以本起点作为 target的起点 其他的孩子也要作为起点 所以pathsum 需要递归
```
public class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0) 
            + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
    }
}
```
