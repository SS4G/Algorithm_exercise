## 637. Average of Levels in Binary Tree
DescriptionHintsSubmissionsDiscussSolution
Discuss   Editorial Solution Pick One
Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

Example 1:
Input:

```
.
    3
   / \
  9  20
    /  \
   15   7
```

Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
Note:
The range of node's value is in the range of 32-bit signed integer

#### tips
用字典记录每一行 然后遍历 最后求平均

#### mycodes


```
class Solution:
    def averageOfLevels(self, root):
        """
        :type root: TreeNode
        :rtype: List[float]
        """
        dictRec = collections.defaultdict(list)
        self.averageHelper(root, 0, dictRec)
        result = []
        for k in dictRec:
            result.append(((sum(dictRec[k]) / len(dictRec[k]), k)))
        result.sort(key=operator.itemgetter(1))
        return [i[0] for i in result]

    def averageHelper(self, root, level, dictRec):
        if root is None:
            return None
        else:
            dictRec[level].append(root.val)
            self.averageHelper(root.left, level + 1, dictRec)
            self.averageHelper(root.right, level + 1, dictRec)
            return None
```
