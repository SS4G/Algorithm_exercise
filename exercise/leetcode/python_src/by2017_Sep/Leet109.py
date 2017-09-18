from AlgorithmTraining.G55Utils.Py.Utils import *


class Solution(object):
    def sortedListToBST(self, head):
        """
        :type head: ListNode
        :rtype: TreeNode
        """
        if head is None:
            return None

        storage = []
        tmp = head
        while tmp is not None:
            storage.append(tmp.val)
            tmp = tmp.next
        return self.recursiveBuild(storage, 0, len(storage))

    def recursiveBuild(self, arr, lo, hi):
        if hi - lo == 0:
            #  print("A")
            return None
        elif hi - lo == 1:
            #  print("B")
            return TreeNode(arr[lo])
        elif hi - lo == 2:
            #  print("C")
            root = TreeNode(arr[hi - 1])
            leaf = TreeNode(arr[lo])
            root.left = leaf
            return root
        elif hi - lo == 3:
            #  print("D")
            root = TreeNode(arr[hi - 2])
            leaf0 = TreeNode(arr[lo])
            leaf1 = TreeNode(arr[hi - 1])
            root.left = leaf0
            root.right = leaf1
            return root
        else:
            # print("E")
            mid = (hi + lo) >> 1
            root = TreeNode(arr[mid])
            root.left = self.recursiveBuild(arr, lo, mid)
            root.right = self.recursiveBuild(arr, mid + 1, hi)
            return root

if __name__ == "__main__":
    s = Solution()
    arr = [0, 1, 2, 3, 4, 5, 6, 7, 8]
    listx = LinkedListUtil.genList(arr)
    root = s.sortedListToBST(listx)
    TreeUtil.showTree(root)
