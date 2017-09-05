# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

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
tree_list=[
    TreeNode(1),#0
    TreeNode(2),#1
    TreeNode(3),#2
    TreeNode(5),#3
    ]
tree_list[0].left =tree_list[1]
tree_list[0].right=tree_list[2]
tree_list[1].right=tree_list[3]
s=Solution()
print(s.binaryTreePaths(tree_list[0]))