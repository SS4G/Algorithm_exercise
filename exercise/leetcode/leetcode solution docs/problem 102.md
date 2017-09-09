## 102. Binary Tree Level Order Traversal  
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).


```
For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
```
##### mycode (beats 56%)
使用层 起始绝对坐标加偏移量的方法来确定一个节点在满二叉树的绝对坐标
不满的二叉树按照满的来看

```
from operator import itemgetter
class Solution(object):
    def __init__(self):
        self.level=0
        self.node_list=[]
    def tra(self,root=None,root_level=0,level_offset=0):
        if root!=None:
            self.node_list.append((self.GetLeStIndex(root_level)+level_offset,root_level,root.val))
            self.tra(root=root.left ,root_level=root_level+1,level_offset=level_offset*2)#left sub tree
            self.tra(root=root.right,root_level=root_level+1,level_offset=level_offset*2+1)#left sub tree

    def GetLeStIndex(self,level):
        """
        get start index in the total tree for level
        :param level:
        :return:
        """
        return 2**(level-1)-1

    def levelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        if root==None:
            return []
        else:
            self.tra(root,1,0)
            node_list2=sorted(self.node_list,key=itemgetter(0),reverse=False)
            cur_level=0
            res= []
            for node in node_list2:
                #print(node)
                if cur_level!=node[1]:
                    res.append([])
                    cur_level=node[1]
                res[cur_level-1].append(node[2])
            return res
```
