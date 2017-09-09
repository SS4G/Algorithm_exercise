## 331. Verify Preorder Serialization of a Binary Tree

One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

```
。。 _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
```

For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:

```
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true
```


Example 2:

```
"1,#"
Return false
```


Example 3:

```
"9,#,#,1"
Return false
```

#### tips
验证一个序列是否是一个正确的先序遍历的序列化二叉树
题目所述的两种不成立得情况包括 某个非空节点有少于两个子树 根据 总节点数应该为奇数可以筛出
另一个是存在已经构成二叉树之外， 序列中有多于的节点 如 eg3

相关题目见Leet449

#### mycode
```Python
class Solution(object):
    def __init__(self):
        self.curIndex = 0

    def isValidSerialization(self, preorder):
        """
        :type preorder: str
        :rtype: bool
        """
        if len(preorder) == 0:
            return False
        if len(preorder.split(","))&0x01 == 0:
            return False
        self.curIndex = 0
        preorder = preorder.split(",")
        return self.isValidRestPart(preorder, self.curIndex) and self.curIndex == len(preorder)

    def isValidRestPart(self, preorder, st):
        if preorder[st] != "#":
            self.curIndex += 1
            if len(preorder) == self.curIndex:
                return False
            resLeft = self.isValidRestPart(preorder, self.curIndex)
            if len(preorder) == self.curIndex:
                return False
            resRight = self.isValidRestPart(preorder, self.curIndex)
            return resLeft and resRight
        else:
            self.curIndex += 1
            return True
```
