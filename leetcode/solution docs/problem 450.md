## 450. Delete Node in a BST Add to List

Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

```
Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
```
#### tips
在删除单个节点时使用非递归的方法
其实这个题目可以完全使用递归来做
但是那样会导致效率低下

本题目的思想是
先找到要删除的关键字的引用 然后在查找的过程中记录这个关键字的父亲节点
然后将要删除的节点的值设置为其右子树的最小值 然后从右子树中递归的删除这个最小值节点
所以实际上只有两次删除操作
第一次删除的是将右子树上的最小值交换到要删除的节点上
第二次是将右子树上的最小值的节点真正的删除
所以很快
其中的对要删除的节点是否是根节点要有特殊的处理

#### mycode(beat 78%)
```
class Solution:

    def deleteNode(self, root, key):
        """
        :type root: TreeNode
        :type key: int
        :rtype: TreeNode
        """
        return self.delete_in_bst(root, key)

    def find_min_node(self, bst_root):
        """
        找出最（左下）小值节点 返回该节点的引用
        :param bst_root:
        :return:
        """
        tmp = bst_root
        while tmp is not None:
            if tmp.left is not None:
                tmp = tmp.left
            else:
                return tmp

    def delete_in_bst(self, bst_root, val, lazy=False):
        """
        删除对应值的节点 lazy 表示是否为懒惰删除
        其实这个方法应该改进为更加递归的方法
        :param bst_root:
        :param val:
        :param lazy:
        :return: 返回删除后的bst的根节点引用
        """
        if lazy:  # lazy delete just mark delete flag for the note
            node = self.find_in_bst(bst_root, val)
            if node is not None:  # the value exist in the bst
                node.del_flag = True
            return bst_root
        else:
            # print("to del value ", val)
            last = bst_root  # 用于跟踪待删除节点的父节点
            tmp = bst_root
            which_son = "self"
            # 找到要删除的值所在的节点 并记录他的父节点 以及他是父节点的哪个孩子
            while tmp is not None:
                if tmp.val > val:
                    last = tmp  # 保存为父节点
                    which_son = "left"
                    tmp = tmp.left
                elif tmp.val < val:
                    last = tmp  # 保存为父节点
                    which_son = "right"
                    tmp = tmp.right
                elif tmp.val == val:  # 查找到对应的节点酒退出
                    break

            if tmp is None:
                return bst_root  # 没有找到要删除的节点
            elif tmp is bst_root:  # 要删除的是bst的根节点
                me = bst_root

                if (me.left is None) and (me.right is None):  # 左右都为空 连根节点都删除了 就返回一个空树
                    return None
                elif (me.left is None) or (me.right is None):  # 左边或者右边为空 直接返回左子树或者右子树
                    return me.left if me.left is not None else me.right
                else:  # 两边全部不为空
                    # 获取右子树的最小值的节点
                    right_min_node = self.find_min_node(bst_root.right)
                    # 将右子树最小值的节点的信息复制到要删除的节点中 实际上并不删除这个节点
                    me.val = right_min_node.val
                    # 要删除的节点的右子树应当更新为删除了最小值节点的右子树
                    me.right = self.delete_in_bst(me.right, me.val)  # 递归的删除右子树中的替换值
                    return bst_root
            else:
                father = last  # 要删除的节点的父节点
                me = tmp  # 要删除的节点

                if (me.left is None) and (me.right is None):  # 要删除的是叶子节点
                    if which_son == "left":
                        father.left = None  # 直接置空付清对应的指针域 让父亲给抛弃自己
                    else:
                        father.right = None

                elif (me.left is None) or (me.right is None):  # 要删除的节点只有一侧子树
                    if which_son == "left":
                        father.left = me.left if me.left is not None else me.right  # 直接将子树拼接到父亲的对应位置
                    else:
                        father.right = me.left if me.left is not None else me.right

                else:  # 两边全部不为空
                    right_min_node = self.find_min_node(me.right)
                    me.val = right_min_node.val  # 同上一部分 并不实际删除 只是修改信息
                    me.right = self.delete_in_bst(me.right, me.val)  # 递归的删除右子树中的替换值
                return bst_root  # 返回根部
```
