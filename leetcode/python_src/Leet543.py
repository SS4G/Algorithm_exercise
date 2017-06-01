from operator import itemgetter
# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution(object):
    def __init__(self):
        self.path_sum = []

    def diameterOfBinaryTree(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        # mark the depth filed in node
        if root is None:
            return 0

        self.mark_depth(root)
        self.path_sum.sort(key=itemgetter(1))
        # 计算路径用的代码 本题不需要
        """
        max_path_node = self.path_sum[-1][0]  # 最大路径的根节点
        left_stack = []
        right_stack = []
        l_ptr = max_path_node.left
        r_ptr = max_path_node.right
        while l_ptr is not None:
            left_stack.append(l_ptr.val)
            l_ptr = l_ptr.left if l_ptr.l_depth > l_ptr.r_depth else l_ptr.right

        while r_ptr is not None:
            right_stack.append(r_ptr.val)
            r_ptr = r_ptr.left if r_ptr.l_depth > r_ptr.r_depth else r_ptr.right

        left_stack.reverse()
        left_stack.append(max_path_node.val)
        left_stack.extend(right_stack)
        return left_stack # 返回从左到右的整条路径
        """
        return self.path_sum[-1][1]

    def mark_depth(self, root):
        if root is None:
            return -1
        else:
            root.l_depth = self.mark_depth(root.left)
            root.r_depth = self.mark_depth(root.right)
            my_depth = max(root.l_depth, root.r_depth) + 1
            root.depth = my_depth

            if root.l_depth == -1 and root.r_depth == -1:  # leaf node
                self.path_sum.append((root, 0))
            elif root.l_depth == -1:
                self.path_sum.append((root, root.r_depth + 1))
            elif root.r_depth == -1:
                self.path_sum.append((root, root.l_depth + 1))
            else:
                self.path_sum.append((root, root.l_depth + root.r_depth + 2))
            return my_depth

# Test
if __name__ == "__main__":
    s = Solution()
    a = TreeNode(1)
    b = TreeNode(2)
    c = TreeNode(3)
    d = TreeNode(4)
    e = TreeNode(5)
    a.left = b
    a.right = c
    b.left = d
    b.right = e
    print(s.diameterOfBinaryTree(a))
