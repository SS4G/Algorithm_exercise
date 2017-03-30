
## 257. Binary Tree Paths  
Given a binary tree, return all root-to-leaf paths.


```
For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
```
#### tips
使用先序遍历 然后使用堆栈 每次遍历一个节点就将这个节点添加到堆栈里面
然后每次递归结束后就从这个堆栈中弹出一个节点

##### mycode (beats 82%)
```
class Solution:
    # @param {TreeNode} root
    # @return {string[]}
    def __init__(self):
        self.path_stack=[]
        self.path_result=[]
    def binaryTreePaths0(self, root):
        if root==None:
            self.path_result.append(self.show_path())
        elif root.left==None and root.right==None:#leaf node
            self.path_stack.append(root.val)
            self.path_result.append(self.show_path())
            self.path_stack.pop()
        elif root.left==None and root.right!=None:#single side node
            self.path_stack.append(root.val)
            self.binaryTreePaths0(root.right)
            self.path_stack.pop()
        elif root.left!=None and root.right==None:
            self.path_stack.append(root.val)
            self.binaryTreePaths0(root.left)
            self.path_stack.pop()
        elif root.left!=None and root.right!=None:#double side node
            self.path_stack.append(root.val)
            self.binaryTreePaths0(root.left)
            self.binaryTreePaths0(root.right)
            self.path_stack.pop()

    def binaryTreePaths(self,root):
        if root==None:
            return []
        else:
            self.binaryTreePaths0(root)
            return self.path_result


    def show_path(self):
        if len(self.path_stack)==0:
            return None
        elif len(self.path_stack)==1:
            return str(self.path_stack[0])
        else :
            return "->".join([str(x) for x in self.path_stack])
```
