
from AlgorithmTraining.data_structure.Base_Knowledge.Tree.BinarySearchTree import BinarySearchTree, bstNode
import random


class AvlNode(bstNode):
    def __init__(self, val, height=-1):
        bstNode.__init__(self, val)
        self.height = height

    def left_height(self):
        return self.left.height if self.left is not None else -1

    def right_height(self):
        return self.right.height if self.right is not None else -1

    def __str__(self):
        return str(self.val)+":"+str(self.height)


class AvlTree(BinarySearchTree):

    def __init__(self):
        BinarySearchTree.__init__(self)

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


class Avl_Test:
    """
    二叉平衡树测试工具
    1 判断所有节点的左右子树的高度差是否小于1
    2 判断这个树是否仍然是一个完整的二叉排序树(中序遍历是否是一个有序的序列)
    """
    def __init__(self):
        self.traverse_list = []

    def mark_nodes_height(self, root):
        node_height_dict = {}
        self.mark_height(root, node_height_dict)
        return node_height_dict

    def mark_height(self, root, node_height_dict):
        """
        返回一个(node,height) 的元组列表 这个列表里面包含了所有的节点的引用
        相当于记录了
        :param root:
        :param node_height_dict:
        :return:
        """
        if root is None:
            node_height_dict[id(None)] = (None, -1)  # 添加None节点 高度为-1
            return -1

        left_height = self.mark_height(root.left, node_height_dict)
        right_height = self.mark_height(root.right, node_height_dict)
        my_height = max(left_height, right_height) + 1
        node_height_dict[id(root)] = (root, my_height)
        return my_height


    def height_balance_test(self, root):
        """
        测试各个节点左右高度是否相同
        :param root:
        :return:
        """
        node_height_dict = self.mark_nodes_height(root)
        for node_id in node_height_dict:
            if node_id != id(None):
                left_id = id(node_height_dict[node_id][0].left)
                right_id = id(node_height_dict[node_id][0].right)
                assert abs(node_height_dict[left_id][1] - node_height_dict[right_id][1]) < 2, \
                    "height balance not meet"

    def part_tree2order(self, root, extern_list):
        if root is not None:
            self.part_tree2order(root.left, extern_list)
            extern_list.append(root.val)
            self.part_tree2order(root.right, extern_list)

    def bst2arr(self, root):
        res_list = []
        self.part_tree2order(root, res_list)
        return res_list

    def order_test(self, root, init_list):
        """
        测试是否仍然是一颗排序树
        :return:
        """
        traverse_list = self.bst2arr(root)
        init_copy = list(set(init_list.copy()))
        init_copy.sort()
        assert traverse_list == init_copy, "order test failure" + \
            "\noriginal list:" + "\t".join([str(i) for i in init_copy]) + \
            "\nnow list:" + "\t".join([str(i) for i in traverse_list])

    def gen_testcases(self):
        test_cases = []
        CASE_MAX_VAL = 200
        CASE_MAX_LEN = 1000
        CASE_MAX_AMOUNT = 1000
        test_cases.append([1, ])
        test_cases.append([1, 2])
        test_cases.append([1, 2, 3, 4, 5])
        test_cases.append([5, 4, 3, 2, 1, 0])
        test_cases.append([10, 11, 12, 13, 14, 15, 5, 4, 3, 2, 1, 0])
        test_cases.append([1, 1, 1, 1, 1, ])
        random.randint(0, 100)
        for i in range(CASE_MAX_AMOUNT-4):
            a = []
            for j in range(random.randint(1, CASE_MAX_LEN)):
                a.append(random.randint(0, CASE_MAX_VAL))
            test_cases.append(a)

        return test_cases

    def save_test_cases(self, testcases):
        f = open("AvlTree_testcase0.txt", "w", encoding="utf-8")
        for testcase in testcases:
            f.write("\t".join([str(i) for i in testcase])+"\n")
        f.close()

    def load_testcases(self):
        f = open("AvlTree_testcase0.txt", "r", encoding="utf-8")
        testcases = [list(map(lambda x: int(x), line.strip().split("\t"))) for line in f]
        f.close()
        return testcases

    def main_testbench(self, operation_list):
        """
        :param operation_list: operations to be execute
        :return:
        """
        for operation in operation_list:
            if operation == "generate_testcase":
                # test step0 :generate testcase and save them in file AvlTree_testcase0.txt
                self.save_test_cases(self.gen_testcases())
            elif operation == "execute_testcase":
                # load the generated testcase
                testcases = self.load_testcases()
                testcases.append([])
                k = 0
                for init_list in testcases:
                    tree_tool = AvlTree()
                    # debug# print(init_list)
                    root = tree_tool.createAvlTree(init_list)
                    # debug# tree_tool.print_tree(root)
                    self.order_test(root, init_list)
                    self.height_balance_test(root)
                    print(k, "case passed!")
                    k += 1
                print("all test passed successfully!")

# Test
if __name__ == "__main__":
    t = Avl_Test()
    # operation_args = ["generate_testcase", "execute_testcase"]
    # operation_args = ["generate_testcase", ]
    operation_args = ["execute_testcase", ]
    t.main_testbench(operation_args)


