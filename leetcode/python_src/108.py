# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class AvlNode(TreeNode):
    def __init__(self, val, height=-1):
        TreeNode.__init__(self, val)
        self.height = height

    def left_height(self):
        return self.left.height if self.left is not None else -1

    def right_height(self):
        return self.right.height if self.right is not None else -1


class AvlTree():

    def __init__(self):
        pass

    def createAvlTree(self, init_list=None):
        root = None
        for val in init_list:
            root = self.Avl_insert(root, val)
        return root

    def Avl_insert(self, root, val):
        """
        这种插入会跟踪的修改路径上节点的高度
        若树中已经有了相同值的节点 则直接放弃 不做任何处理
        一个节点的高度为 max(left_height,right_height)+1
        :param root:
        :param val:
        :return:
        """
        if root is None:
            return AvlNode(val=val, height=0)

        path_stack = []
        ptr = root
        # step1:现将新来的节点插入导致指定的位置上
        while ptr is not None:  # insert the node first
            path_stack.append(ptr)
            if val < ptr.val:
                if ptr.left is None:  # insert here
                    ptr.left = AvlNode(val=val, height=0)
                    path_stack.append(ptr.left)
                    break
                else:  # you still need to find
                    ptr = ptr.left
            elif val > ptr.val:
                if ptr.right is None:  # insert here
                    ptr.right = AvlNode(val=val, height=0)
                    path_stack.append(ptr.right)
                    break
                else:  # you still need find
                    ptr = ptr.right
            else:  # the value is already exist
                return root

        # back track to find the out of balanced tree node
        # father_flag 的枚举常量记录当前要旋转的树是他父亲的左节点还是右节点 还是他根本就没有父亲
        NO_FATHER = 0  # 没有父亲
        FATHER_LEFT_SON = 1  # 是左儿子
        FATHER_RIGHT_SON = 2  # 是右儿子

        # step2: 使用之前堆栈跟踪的插入路径 自下而上的检查一个节点的左右子树高度是否差值小于1 如果不是 进行递归的调整
        res_tree_root = None  # 最终的结果的root
        while len(path_stack) > 0:

            bottom_node = path_stack.pop()
            # 获取左右子树的高度信息
            left_tree_height = bottom_node.left_height()
            right_tree_height = bottom_node.right_height()
            bottom_node.height = 1 + max(left_tree_height, right_tree_height)

            if abs(left_tree_height-right_tree_height) >= 2:  # need to rotate to adjust avl tree
                if len(path_stack) == 0:
                    father_flag = NO_FATHER
                elif path_stack[-1].left is bottom_node:
                    father_flag = FATHER_LEFT_SON
                elif path_stack[-1].right is bottom_node:
                    father_flag = FATHER_RIGHT_SON
                else:
                    assert False, "程序有bug 居然儿子不是自己的？？？？接盘了？？"
                is_left = True if left_tree_height > right_tree_height else False
                b = (bottom_node.left if is_left else bottom_node.right)
                is_single_rotate = True if ((is_left and (b.right_height() < b.left_height())) or
                ((not is_left) and (b.right_height() > b.left_height()))) else False
                # 左子树的外侧最高 或 右子树的外侧最高 时选择单旋转 否则选择双旋转

                # 根据上一步骤的分析进行相应的旋转
                # 旋转过程中 旋转函数会自动的更新树中的高度域 这样更高效一些
                if is_single_rotate:
                    rotated_root = self.single_rotate_tree(bottom_node, is_left=is_left)
                else:
                    rotated_root = self.double_rotate_tree(bottom_node, is_left=is_left)

                if father_flag == NO_FATHER:
                    res_tree_root = rotated_root  # 如果调整的树包含了整个树的根节点 那么返回调整后的节点
                elif father_flag == FATHER_LEFT_SON:
                    path_stack[-1].left = rotated_root
                elif father_flag == FATHER_RIGHT_SON:
                    path_stack[-1].right = rotated_root
                else:
                    assert False, "我觉得到不了这里"
            else:
                res_tree_root = bottom_node

        # debug:# print("inner")
        # debug:# self.print_tree(res_tree_root)

        return res_tree_root

    def single_rotate_tree(self, root, is_left=True):
        """
        a b 是单层节点 c d e 是树（包括空树的情况）
        仅仅以左侧不平衡的情况为例子
        正确的情况是ce是高度一样的树 d.height<=c.height
        现在在左侧 c的高度 大于 e的高度
               a           b
              / \         / \
             b  e  ----> c  a
            /\             / \
           c d            d  e
        :param root:
        :param is_left:
        :return:
        """
        if is_left:
            a = root
            b = a.left
            # c = b.left
            d = b.right
            # e = a.right
            b.right = a
            a.left = d
        else:
            a = root
            b = a.right
            # c = b.right
            d = b.left
            # e = a.left
            b.left = a
            a.right = d

        # pay attention to the order when update the height of the tree
        # from the bottom to the top
        a.height = max(a.left_height(), a.right_height())+1  # update the height after adjust
        b.height = max(b.left_height(), b.right_height())+1
        return b

    def double_rotate_tree(self, root, is_left=True):
        """
        a b c 是单个节点 d f g 是树 高度一样
            a                 c
           / \               / \
          b  d              b  a
         / \               /\  /\
        e  c     ---->    e f g d
          / \
         f  g
        :param root:
        :param is_left:
        :return:
        """
        if is_left:
            a = root
            b = a.left
            d = a.right
            e = b.left
            c = b.right
            f = c.left
            g = c.right
            c.left = b
            c.right = a
            b.left = e
            b.right = f
            a.left = g
            a.right = d
        else:
            a = root
            b = a.right
            d = a.left
            e = b.right
            c = b.left
            f = c.right
            g = c.left
            c.right = b
            c.left = a
            b.right = e
            b.left = f
            a.right = g
            a.left = d

        # pay attention to the order when update the height of the tree
        # from the bottom to the top
        a.height = max(a.left_height(), a.right_height())+1  # update the height after adjust
        b.height = max(b.left_height(), b.right_height())+1
        c.height = max(c.left_height(), c.right_height())+1
        return c


class Solution(object):

    def __init__(self):
        self.tool = AvlTree()

    def sortedArrayToBST(self, nums):
        """
        :type nums: List[int]
        :rtype: TreeNode
        """
        return self.tool.createAvlTree(nums)