## 606. Construct String from Binary Tree My SubmissionsBack To Contest

You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

Example 1:
Input: Binary tree: [1,2,3,4]

```
。     1
     /   \
    2     3
   /    
  4
```

Output: "1(2(4))(3)"

Explanation: Originallay it needs to be "1(2(4)())(3()())", 
but you need to omit all the unnecessary empty parenthesis pairs. 
And it will be "1(2(4))(3)".
Example 2:
Input: Binary tree: [1,2,3,null,4]

```
.      1
     /   \
    2     3
     \  
      4
```

Output: "1(2()(4))(3)"

Explanation: Almost the same as the first example, 
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.

#### tips

[官方的题解](https://leetcode.com/articles/construct-string-from-binary-tree/)

这个题目有点不太清楚的地方就是 如果亦可二叉树是如下的这个样子 那么 1 的右边的空节点的 空括号对也是需要去掉的 最后的结果应该是1(2(3))

```
.   1
   / 
  2
 /
3
```
所以我的做法是 让所有的右边节点的空括号 压根就不要出来 
即 两个子树都为空 不添加括号 ，右边子树为空 不添加括号
只有左边子树为空添加空括号。
这样做就可以了 也很简单

#### mycode

```
class Solution(object):
    def tree2str(self, t):
        """
        :type t: TreeNode
        :rtype: str
        """
        output = []
        self.traverse(t, output)
        return "".join(output)

    def traverse(self, root, output):
        if root is None:
            return
        else:
            output.append(str(root.val))
            if root.left is not None or root.right is not None:
                output.append("(")
                self.traverse(root.left, output)
                output.append(")")
                if root.right is not None:
                    output.append("(")
                    self.traverse(root.right, output)
                    output.append(")")
```
