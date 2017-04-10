# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def __init__(self):
        self.this_mode = None
        self.this_cnt = 0
        self.res_list = [0, []]  # [max val, max_num_list]

    def findMode(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        self.this_mode = -100001001001
        self.this_cnt = -1
        self.res_list = [-1, []]  # [max amount, max_num_list]

        if root is None:
            return []
        self.traverse(root)
        if self.this_cnt > self.res_list[0]:  # refresh the reslist
            self.res_list[0] = self.this_cnt
            self.res_list[1] = [self.this_mode, ]
        elif self.this_cnt == self.res_list[0]:
            self.res_list[1].append(self.this_mode)
        return self.res_list[1]


    def traverse(self, root):
        if root is not None:
            self.traverse(root.left)
            if root.val != self.this_mode:
                self.old_mode = self.this_mode  # save old
                self.old_cnt = self.this_cnt

                self.this_cnt = 1
                self.this_mode = root.val
                if self.old_cnt > self.res_list[0]:  # refresh the reslist
                    self.res_list[0] = self.old_cnt
                    self.res_list[1] = [self.old_mode,]
                elif self.old_cnt == self.res_list[0]:
                    self.res_list[1].append(self.old_mode)
            else:
                self.this_cnt += 1

            self.traverse(root.right)

if __name__ == "__main__":
    a = TreeNode(1)
    b = TreeNode(1)
    c = TreeNode(1)
    d = TreeNode(2)
    a.left = b
    a.right = c
    c.right = d
    s = Solution()
    print(s.findMode(a))
