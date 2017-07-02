## 508. Most Frequent Subtree Sum
Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:


```
5
 /  \
2   -3
```

return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:


```
5
 /  \
2   -5
```

return [2], since 2 happens twice, however -5 only occur once.

#### tips
这个吗后序递归并统计即可

#### mycode

```
import operator


class Solution(object):
    def findFrequentTreeSum(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root is None:
            return []

        outputDict = {}
        self.getSumRecursive(root, outputDict)
        itesm0 = list(outputDict.items())
        itesm0.sort(key=operator.itemgetter(1), reverse=True)
        maxFreq = itesm0[0][1]
        res = []
        for i in range(len(itesm0)):
            if itesm0[i][1] == maxFreq:
                res.append(itesm0[i][0])  # add key
            else:
                break
        return res

    def getSumRecursive(self, root, outputDict):
        if root is None:
            return 0
        else:
            leftSum = self.getSumRecursive(root.left, outputDict)
            rightSum = self.getSumRecursive(root.right, outputDict)
            thisSum = leftSum + rightSum + root.val
            outputDict.setdefault(thisSum, 0)
            outputDict[thisSum] += 1
            return thisSum
```
