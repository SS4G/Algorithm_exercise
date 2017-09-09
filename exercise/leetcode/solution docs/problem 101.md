# leetcdoe 101
## Question 
#### Symmetric Tree


## Answer 
#### 方法一
采用第100题的判断两个二叉树是否相同的函数 issameTree(p,q)
和 第226题的invertbinaryTree()
先把主树的两个子树拆分开 翻转其中的一颗子树
然后比较这两颗子树是否完全相同，若完全相同 则说明这课树是镜像的
##### code (beat 40% Pyrhon)

```
class Solution(object):
    def invertTree(self, root):
        """
        :type root: TreeNode
        :rtype: TreeNode
        """
        if root!=None:
            if root.left!=None or root.right!=None:
                root.left,root.right=root.right,root.left
                self.invertTree(root.left)
                self.invertTree(root.right)
        return root
        

    def isSameTree(self, p, q):
        """
        :type p: TreeNode
        :type q: TreeNode
        :rtype: bool
        """
        if p!=None and q!=None:
            if p.val!=q.val:
                return False 
                
            if self.isSameTree(p.left,q.left) and self.isSameTree(p.right,q.right):
                return True
            else :
                return False
        elif p==None and q==None:
            return True
        else:
            return False
            


    def isSymmetric(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        if root==None:
            return True
        elif root.left==None and root.right==None:
            return True
        elif root.left!=None and root.right!=None:
            reversed_left=self.invertTree(root.left)
            return isSameTree(reversed_left,root.right)
        else:
            return False
```

#### 方法二
对传入的树的每个节点进行标记 跟他们增加一个在二叉树中的绝对位置域
同时把这些节点的引用放在
然后根据这个域对节点进行排序 根据二叉树的位置 拆分比较各个行的节点的值是否是对称的
但是这样做如果直接用数组存放满二叉树的话如果有大量的None 将导致内存超限
 避免稀疏二叉树状况的发生

##### code (MLE) 
```
#Final state:MLE
class Solution(object):
    def __init__(self):
        self.mark_list=[]
        self.max_line=0

    def line_offset(self,line):
        """ 
        root node index is 0 
        line index start from 1
        empty tree has 0 line
        """
        if line==1:
            return 0
        else:
            return 2**(line-1)-1
    
    def mark_tree_node(self,root,line):        
        """
        :type root: TreeNode
        :rtype: List[TreeNode,]
        """
        if root!=None:
            if self.max_line<line+1:
                self.max_line=line+1
            
            self.mark_list.append(root)
            if root.left!=None:
                root.left.abs_index =self.line_offset(line+1)+(root.abs_index-self.line_offset(line))*2
                if self.max_line<line+1:
                    self.max_line=line+1
                self.mark_tree_node(root.left,line+1)
            if root.right!=None:
                root.right.abs_index=self.line_offset(line+1)+(root.abs_index-self.line_offset(line))*2+1
                if self.max_line<line+1:
                    self.max_line=line+1
                self.mark_tree_node(root.right,line+1)
        return None
    
    def convert_tree_to_list(self,root):
        """
        :type root: TreeNode
        :rtype: List[TreeNode,]
        """
        if root!=None:
            root.abs_index=0
            self.mark_tree_node(root,1)
            
            tree_node_array=[None,]*self.line_offset(self.max_line+1)
            for i in self.mark_list:
                tree_node_array[i.abs_index]=i

                
            return tree_node_array
        else :
            return [None,]


    
    def isSymmetric(self,root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        tree_node_array=self.convert_tree_to_list(root)    
        if tree_node_array[0]==None:
            return True
        elif len(tree_node_array)==1:
            return True
        else:
            for line_index in range(2,self.max_line+1):

                if not self.check_symmetry(tree_node_array[self.line_offset(line_index):self.line_offset(line_index+1)]):
                    return False
            return True
                    
                    
    def check_symmetry(self,node_list):    
        length=len(node_list)
        length_2=length>>1
       
        if length&0x01!=0:
            return False
        else:
            for i in range(length_2):
                if node_list[i]==None and node_list[length-1-i]==None:
                    continue 
                elif node_list[i]!=None and node_list[length-1-i]!=None:
                    if node_list[i].val!=node_list[length-1-i].val:
                        return False   
                else :
                    return False
            return True
```
