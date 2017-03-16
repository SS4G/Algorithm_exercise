# coding=utf-8
# Author: SS4G
# Date 2017/03/09
# Status:finished


class Node:
    """
    二叉树的节点
    """
    def __init__(self, val=None, node_index=-1):
        """
        :param val:
        """
        self.val = val  # 节点值
        self.left = None  # 左孩子的引用
        self.right = None  # 有孩子的引用
        self.index = node_index  # 在满二叉树中的绝对位置 根节点为0

    def __str__(self):
        return str(self.val)+","+str(self.index)


class BinaryTree:
    """
    二叉树的相关方法
    该二叉树是链式结构

    统一标准:
    二叉树的根节点编号为0
    根节点所在的层为0
    """
    def __init__(self):
        pass

    def create_binary_tree(self, init_val_list=None):
        """
        根据列表中的值创建一个二叉树
        :param init_val_list:
        """
        #   0
        #  / \
        # 1   2 ...
        # 若以0 为开始的根节点
        # 一个节点在满二叉树中的坐标为n 坐标标记方法为从左到右 从上到下
        # 其左孩子的坐标为 2*n+1 右孩子的坐标为 2*n+2
        length = len(init_val_list)
        if init_val_list is not None:
            root = Node(val=init_val_list[0], node_index=0)
            self.add_node(root, 0, length, init_val_list)
        else:
            root = None
        return root

    def add_node(self, this_node=None, this_node_index=0, init_val_length=0, init_arr=None):
        """
        :param this_node: 当前节点的 引用
        :param this_node_index: 当前节点在满二叉树中的位置
        :param init_val_length: 初始化时yo
        :param init_arr: 初始值使用的列表
        :return:
        """
        left_son_index = this_node_index*2+1
        right_son_index = this_node_index*2+2
        if left_son_index < init_val_length:
            val = init_arr[left_son_index]
            if val is not None:
                this_node.left = Node(val, 2*this_node_index+1)
                self.add_node(this_node.left, this_node_index=2*this_node_index+1,
                              init_val_length=init_val_length, init_arr=init_arr)
        if right_son_index < init_val_length:
            val = init_arr[right_son_index]
            if val is not None:
                this_node.right = Node(val, 2*this_node_index+2)
                self.add_node(this_node.right, this_node_index=2*this_node_index+2,
                              init_val_length=init_val_length, init_arr=init_arr)

    def print_tree(self, tree, level=0, level_mark="····"):
        """
        将二叉树按照层次结构打印出来
        使用类似于文件目录结构的方式
        tree 为根节点时 level参数使用默认的0即可
        :param tree: 当前的节点
        :param level:当前打印的节点所处的层数
        :param level_mark: 打印每层前面的字符
        :return:
        """
        if tree is None:  # the node is leaf
            return None
        else:
            print(" " + level * level_mark + str(tree))
            if (tree.left is None) and (tree.right is not None):  # 确保在左右子树只有一个的情况下不会分不清左右
                print(" " + (level+1) * level_mark+"-")
            self.print_tree(tree.left, level=level+1)
            if (tree.right is None) and (tree.left is not None):
                print(" " + (level+1) * level_mark+"-")
            self.print_tree(tree.right, level=level + 1)

    def recursion_pre_traverse(self, root):
        """
        递归前序遍历
        :return: 返回一个遍历结果的值的列表
        """
        traverse_res = []
        if root is not None:
            traverse_res.append(root.val)
            traverse_res.extend(self.recursion_pre_traverse(root.left))
            traverse_res.extend(self.recursion_pre_traverse(root.right))
        return traverse_res

    def recursion_mid_traverse(self, root):
        """
        递归中序遍历
        :return: 返回一个遍历结果的值的列表
        """
        traverse_res = []
        if root is not None:
            traverse_res.extend(self.recursion_mid_traverse(root.left))
            traverse_res.append(root.val)
            traverse_res.extend(self.recursion_mid_traverse(root.right))
        return traverse_res

    def recursion_post_traverse(self, root):
        """
        递归后序遍历
        :return: 返回一个遍历结果的值的列表
        """
        traverse_res = []
        if root is not None:
            traverse_res.extend(self.recursion_post_traverse(root.left))
            traverse_res.extend(self.recursion_post_traverse(root.right))
            traverse_res.append(root.val)
        return traverse_res

    def non_recursion_pre_traverse(self, root):
        """
        非递归前序遍历
        :return: 返回一个遍历结果的值的列表
        """
        travese_res = []
        stack = []
        if root is not None:
            stack.append(root)
            head = root
            while stack

    def non_recursion_mid_traverse(self):
        """
        非递归中序遍历
        :return: 返回一个遍历结果的值的列表
        """

    def non_recursion_post_traverse(self):
        """
        非递归后序遍历
        :return: 返回一个遍历结果的值的列表
        """

    def level_order_traverse(self):
        """
        二叉树层次遍历
        :return: 返回一个遍历结果的值的列表的列表
        每个子列表 代表一个层次 根节点所在的层为0
        """


# Test
if __name__ == "__main__":

    binarytree = BinaryTree()
    tree = binarytree.create_binary_tree(["A", "B", "C", "D", "E", "F", "G", "H", None, None, "I"])
    binarytree.print_tree(tree)
    pre_traverse_res = binarytree.recursion_pre_traverse(tree)
    print(",".join(pre_traverse_res))
    mid_traverse_res = binarytree.recursion_mid_traverse(tree)
    print(",".join(mid_traverse_res))
    post_traverse_res = binarytree.recursion_post_traverse(tree)
    print(",".join(post_traverse_res))
